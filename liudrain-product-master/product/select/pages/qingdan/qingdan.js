// pages/qingdan/qingdan.js
const urlList = require('../../config.js')
var http = require('../../utils/http.js');
var utils = require('../../utils/utils.js');
var app = getApp()
Page({

  /**
   * 页面的初始数据
   */
  data: {
    productList: [],
    nLocation: 1,
    statusTip: false,
    notone: false,
    level: 0
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function(options) {

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

    var level = app.globalData.level
    this.setData({
      level: level
    })
    this.getFocus()
  },
  toDetail: function(e) {
    console.log("详情处理")
    var productId = e.currentTarget.dataset.id

    if (utils.getLevel()) {
      wx.navigateTo({
        url: '../productDetail/productDetail?productId=' + productId
      })
    }

  },
  getFocus: function() {
    var that = this;
    http.getReq(urlList.getFocus, function(res) {
      console.log(res)
      if (res.result.length == 0) {
        that.setData({
          notone: true
        })
      } else {
        that.setData({
          productList: res.result,
          notone: false
        })
      }

    }, true)
  },
  focus: function(e) {
    console.log(e.currentTarget.dataset.type)

    var that = this
    var data = {
      productId: e.currentTarget.dataset.id,
      type: e.currentTarget.dataset.type
    }
    http.postReq(urlList.focus, data, function(res) {
      console.log(res)
      that.getFocus()
    }, true)


  },

  compare: function() {
    console.log(utils.getLevel())
    var productList = this.data.productList

    if (utils.getLevel()) {

      if (productList.length < 2) {
        wx.showToast({
          title: '至少有两款产品才能比较',
          icon: 'none',
          duration: 2000
        })
      } else {
        wx.navigateTo({
          url: '../compare/compare',
        })
      }
    }


  }
})