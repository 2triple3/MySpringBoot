const app = getApp()

var header = {
  'Accept': 'application/json',
  'content-type': 'application/x-www-form-urlencoded ',
}
/**
* url: 请求地址
* cb： 回调函数
* toast作用： 请求接口时， 显示loadding， 默认"显示"
*/
function getReq(url, cb, toast = true) {
  var userId = wx.getStorageSync('userId') || ''
  if (userId) {
    header['userId'] = userId;
  }
  if (toast) {
    wx.showLoading({
      title: '加载中',
    })
  }
  console.log("userId==" + userId),
  wx.request({
    url: url,
    method: 'get',
    header: header,
    success: function (res) {
      if (toast) {
        wx.hideLoading();
      }
      return response_handle(res, cb);
    },
    fail: function () {
      if (toast) {
        wx.hideLoading();
      }
      wx.showModal({
        title: '网络错误',
        content: '网络出错，请刷新重试',
        showCancel: false
      })
      return typeof cb == "function" && cb(false)
    }
  })
}

/**
* url: 请求地址
* data: 参数
* cb： 回调函数
* toast作用： 请求接口时， 显示loadding， 默认"显示"
*/
function postReq(url, data, cb, toast = true) {
  var userId = wx.getStorageSync('userId') || ''
  if (userId) {
    header['userId'] = userId;
  }
  if (toast) {
    wx.showLoading({
      title: '加载中',
    })
  }

  wx.request({
    url: url,
    header: header,
    data: data,
    method: 'post',
    success: function (res) {
      if (toast) {
        wx.hideLoading();
      }
      return response_handle(res, cb);
    },
    fail: function () {
      if (toast) {
        wx.hideLoading();
      }
      wx.showModal({
        title: '网络错误',
        content: '网络出错，请刷新重试',
        showCancel: false
      })
      return typeof cb == "function" && cb(false)
    }
  })

}

//返回处理
function response_handle(res, cb) {
  if (res.data.status!=1){
    wx.showToast({
      title: res.message,
      mask: false
    })
  }
  return typeof cb == "function" && cb(res.data)
}

module.exports = {
  getReq: getReq,
  postReq: postReq
}