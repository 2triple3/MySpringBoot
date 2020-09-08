// pages/pay/pay.js
const WxParse = require('../../wxParse/wxParse.js');
const urlList = require('../../config.js')
var http = require('../../utils/http.js');
var utils = require('../../utils/utils.js');
var app = getApp()

Page({

  /**
   * 页面的初始数据
   */
  data: {
    date1: '',
    date2: '',
    price: '',
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function(options) {

    var date1 = utils.getDateStr(null, 0)
    var date2 = utils.getDateStr(null, 365)
    console.log(date1)
    console.log(date2)

    var that = this

    http.getReq(urlList.getPrice, function(res) {
      console.log(res)
      that.setData({
        price: res.result[0],
        date1: date1,
        date2: date2,
      })
    }, true)

    var data = {
      type: 1
    }
    http.postReq(urlList.getContent, data, function (res) {
      that.setData({
        content: res.result
      })
    }, true)


  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function() {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function() {

  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function() {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function() {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function() {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function() {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function() {

  },
  pay: function(e) {
    var priceId = e.currentTarget.dataset.id
    var data = {
      priceId: priceId
    }
    http.postReq(urlList.getPay, data, function(res) {
      wx.requestPayment({
        timeStamp: res.result.timeStamp,
        nonceStr: res.result.nonceStr,
        package: res.result.package,
        signType: res.result.signType,
        paySign: res.result.paySign,
        success(res) {

          var data = {
            level: 1
          }
          http.postReq(urlList.getLevel, data, function(res) {
            console.log(res)
            if (res.status == 1) {
              wx.showToast({
                title: '您已经成功开通善问会员，马上开始探索之旅！',
                icon: 'none',
                duration: 2000
              })
              app.globalData.level = 1
            } else {
              wx.showToast({
                title: '网络错误，请联系客服为您处理！',
                icon: 'none',
                duration: 2000
              })
            }
          }, false)

          setTimeout(function() {
            wx.navigateBack({
              delta: 2
            })
          }, 2000)

        },
        fail(res) {
          wx.showToast({
            title: '支付失败，不能为您提供完整服务',
            icon: 'none',
            duration: 2000
          })


        }
      })
    }, true)
  },
})