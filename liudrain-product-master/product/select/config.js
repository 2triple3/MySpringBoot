//const basePath = 'https://api.adong.fun';
const basePath = 'http://localhost:8888/shanwen';
const urlList = {
  // refreshTokeUrl: basePath + '/refreshToke',//token
  // 登录获取保存用户信息
  basePath: basePath,
  loginUrl: basePath + '/wxapp/getopenId', //
  getHomeArticle: basePath + '/wxapp/getHomeArticle', //获取首页文章
  getArticleList: basePath + '/wxapp/getArticleList', //获取文章列表
  getUserInfo: basePath + '/wxapp/getUserInfo', //获取用户信息
  getPrice: basePath + '/wxapp/getPrice', //获取当前会员价格
  getPay: basePath + '/wxpay/first', //微信支付预下单
  getLevel: basePath + '/wxapp/getLevel', //微信支付预下单
  getFocus: basePath + '/wxapp/getFocus', //获取关注的产品列表
  getQuery: basePath + '/wxapp/getQuery', //查询
  getProductList: basePath + '/wxapp/getProductList', //查询产品list
  getProduct: basePath + '/wxapp/getProduct',
  focus: basePath + '/wxapp/focus', //关注产品list
  getSelect: basePath + '/wxapp/getSelect',
  getFocusProduct: basePath + '/wxapp/getFocusProduct',  //获取关注的产品的详情比对详情
  getContent: basePath + '/wxapp/getContent'//获取有问必答的说明和购买页面的说明
}
module.exports = urlList;