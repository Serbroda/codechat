import Router from 'koa-router';
import { Controller } from './controller';

export default class PingController extends Controller {
  constructor() {
    super('/api/v1/ping');
  }

  public initRoutes(router: Router): void {
    router.get(`${this.baseUrl}`, async (ctx) => (ctx.body = 'Ping!!'));
  }
}
