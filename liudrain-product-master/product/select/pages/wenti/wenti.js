// pages/wenti/wenti.js
const WxParse = require('../../wxParse/wxParse.js');

const urlList = require('../../config.js')
var http = require('../../utils/http.js');
var app = getApp()
Page({

  /**
   * 页面的初始数据
   */
  data: {
    level: 0,
    content:''
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function(options) {
    var level = app.globalData.level;
      var that =this 
    var  data={
      type:2
    } 
    http.postReq(urlList.getContent,data,function(res){
      that.setData({
        content: res.result
      })
    },true),


    this.setData({
      level: level
    })
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
    var that = this;
    http.getReq(urlList.getUserInfo, function (res) {
      console.log(res)
      app.globalData.level = res.result.level
      that.setData({
        level: res.result.level
      })
    }, true)
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

  }
})