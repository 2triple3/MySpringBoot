// pages/zhinan/zhinan.js
const urlList = require('../../config.js')
var http = require('../../utils/http.js');
var utils = require('../../utils/utils.js');
var app = getApp()

Page({
  /**
   * 页面的初始数据
   */
  data: {
    url: '',
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var that=this 
    var id = options.id
    if (options.id != undefined){
      wx.getStorage({
        key: 'userId',
        success: function(res) {
          var url = urlList.basePath + "/chargeArticle/getArticle?articleId=" + id + "&userId=" + res.data
          that.setData({
            url: url
          })
        },
      })
     
    }
    if (options.url != undefined) {
      that.setData({
        url: options.url
      })
    }

  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {
  
  },
 

})