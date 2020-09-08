// pages/compare/compare.js

const urlList = require('../../config.js')
var http = require('../../utils/http.js');
var utils = require('../../utils/utils.js');
var app = getApp()


Page({

  /**
   * 页面的初始数据
   */
  data: {
    selectindex: 0,
    productNum: 8,
    productList: [],
    items: null

  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function(options) {
    this.getFocusProduct(null);
  },
  getFocusProduct: function(types) {
    var that = this
    var data = {
      types: types
    }
    http.postReq(urlList.getFocusProduct, data, function(res) {
      console.log(res.result)
      that.setData({
        productList: res.result,
        items: res.result[0],
        productNum: res.result.length
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


  selectFilter: function() {
    wx.navigateTo({
      url: '../filtrate/filtrate',
    })
  },
  delFocus: function(e) {
    console.log(e.target.dataset.id)
    var that = this
    var productNum = that.data.productNum
    if (productNum == 2) {
      wx.showToast({
        title: '至少有两个产品！',
        icon: 'none',
        duration: 2000
      })
    } else {
      var data = {
        productId: e.target.dataset.id,
        type: 2
      }
      http.postReq(urlList.focus, data, function(res) {
        console.log(res)
        that.onLoad()
      }, true)
    }

  },

  clickLine: function(e) { //点击事件，进行显隐控制
    var that = this;
    var index = e.currentTarget.dataset.index;
    var decisionClassId = e.currentTarget.dataset.classid;
    var list = that.data.productList;
    for (var j = 0; j < list.length; j++) {
      for (var i = 0; i < list[j]['decisions'].length; i++) {
        if (list[j]['decisions'][i].decisionClassId == decisionClassId) {
          if (list[j]['decisions'][i].hidden == true) {
            list[j]['decisions'][i].hidden = false;
          } else {
            list[j]['decisions'][i].hidden = true;
          }
        }
      }
    }
    that.setData({
      productList: list,
      items: list[0]
    })
  },
  checkboxChange: function(e) {
    var types = e.detail.value
    var that = this
    if (types.length == 0) {
      that.onLoad()
    } else {
      that.getFocusProduct(types)
    }
  },
  wenti: function() {
    wx.switchTab({
      url: '../wenti/wenti',
    })
  },

})