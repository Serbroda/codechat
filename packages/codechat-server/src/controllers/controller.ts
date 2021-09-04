import fs from 'fs';
import path from 'path';
import Router from 'koa-router';
import { ParameterizedContext } from 'koa';

export interface BodyInfo {
  type?: string;
  body?: any;
  statusIfUndefined?: number;
}

export abstract class Controller {
  constructor(protected baseUrl: string = '') {}
  public abstract initRoutes(router: Router): void;
}

export const bootstrapControllers = async (router: Router): Promise<Controller[]> => {
  return new Promise<Controller[]>((resolve, reject) => {
    const controllers: Controller[] = [];
    fs.readdirSync(__dirname)
      .filter((f) => fs.lstatSync(path.join(__dirname, f)).isFile() && /^[^.]*\.[^.]*\.(ts|js)$$/.test(f))
      .forEach(async (c) => {
        const modules = await import(path.join(__dirname, c));
        const controller = tryCreateModule(modules.default);
        if (controller) {
          controller.initRoutes(router);
          controllers.push(new modules.default(router) as Controller);
        }
      });
    resolve(controllers);
  });
};

function tryCreateModule(mod: any): Controller | undefined {
  try {
    const module = new mod();
    if (isController(module)) {
      return module as Controller;
    }
    return undefined;
  } catch (err) {
    return undefined;
  }
}

export const isController = (obj: any): obj is Controller => {
  const p = obj as Controller;
  return p.initRoutes !== undefined;
};

export const response = (ctx: ParameterizedContext, status?: number, body?: BodyInfo) => {
  const statusIfUndefined = body?.statusIfUndefined ? body.statusIfUndefined : 204;
  if (status) {
    ctx.status = status;
  }
  if (body?.type) {
    ctx.type = body.type;
  } else {
    ctx.type = 'application/json';
  }
  if (body?.body) {
    ctx.body = body?.body;
  } else if (statusIfUndefined) {
    ctx.status = statusIfUndefined;
  }
};

export const ok = (ctx: ParameterizedContext, body?: BodyInfo) => {
  response(ctx, 200, body);
};

export const badRequest = (ctx: ParameterizedContext, body?: BodyInfo) => {
  response(ctx, 400, body);
};

export const notFound = (ctx: ParameterizedContext, body?: BodyInfo) => {
  response(ctx, 404, body);
};

export const noContent = (ctx: ParameterizedContext) => {
  response(ctx, undefined, undefined);
};
