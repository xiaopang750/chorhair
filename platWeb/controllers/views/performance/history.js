/** *description:view-control-历史订单 *author:fanwei *date:2015/3/2 */module.exports = function(app, route, preRoute) {    var Class = app.get('Class');    var baseController = require('../../../lib/baseController')(app);    var History = Class.create(baseController, {        initialize: function() {            var _this = this;            this.loadView({                app: app,                route: route,                beforeRender: function(req, res, page, commonData) {                    _this.renderPage(req, res, page, commonData);                }            });        }    });    var oHistory = new History();};