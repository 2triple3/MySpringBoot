
//app.js
const urlList = require('/config.js')
var http = require('/utils/http.js');
App({
  globalData: {
    hasPhone: false,
    level:0,
    isfiltrate:0,
    quedian:0
  },
  onLaunch: function () {
    // 登录
    var that = this;
    wx.setStorageSync('userId', '3a33884275cb41eda029f55bef9d73fb')
    wx.login({
      success: res => {
        // 发送 res.code 到后台换取 openId, sessionKey, unionId
        wx.request({
          url: urlList.loginUrl,
          data: {
            code: res.code,
          },
          header: {
            'content-type': 'application/json' // 默认值
          },
          success(res) {
            that.globalData.level = res.data.result.level;
            //wx.setStorageSync('userId', res.data.result.userId)  
          }
        })
      }
    })
    
    http.getReq(urlList.getSelect, function (res) {
      console.log(res.result)
      that.globalData.params = res.result.params
      that.globalData.sorts = res.result.sorts
      that.globalData.brand = res.result.brandList
      that.globalData.low =0
      that.globalData.heigh =30000
  
    }, true)

  },
  getCurrentPages: function () {
    　　var pages = getCurrentPages();    //获取加载的页面
    　　var currentPage = pages[pages.length - 1];  //获取当前页面的对象
    　　var url = currentPage.route;  //当前页面url
    　　var options = currentPage.options;   //获取url中所带的参数
    　　//拼接url的参数
    　　var currentPage = url + '?';
    　　for (var key in options) {
      　　　　var value = options[key]
      　　　　currentPage += key + '=' + value + '&';
    　　}
    　　currentPage = currentPage.substring(0, currentPage.length - 1);
    　　return currentPage;
  },
})