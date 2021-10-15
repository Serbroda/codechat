import Koa from 'koa';
import Router from 'koa-router';
import { bootstrapControllers } from './controllers/controller';

export interface ServerProperties {
  port: number;
}

export default class Server {
  private readonly _props: ServerProperties;
  private _app: Koa | undefined;
  private _server: any;

  constructor(properties?: Partial<ServerProperties>) {
    this._props = { ...{ port: 3030 }, ...properties };
  }

  public async start() {
    if (!this._app) {
      this._app = new Koa();
    }

    const router = new Router();
    await bootstrapControllers(router);
    this._app.use(router.routes());
    router.stack.forEach((r) => console.log(`   => ${r.path}`));

    this._server = this._app.listen(this._props.port);
    console.log(`Server listening on http://localhost:${this._props.port}`);
  }

  public stop() {
    if (this._server) {
      this._server.close();
      this._server = undefined;
    }
  }
}
