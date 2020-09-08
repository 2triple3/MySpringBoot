const WxParse = require('../../wxParse/wxParse.js');
const urlList = require('../../config.js')
var http = require('../../utils/http.js');
var utils = require('../../utils/utils.js');
var t, e, o = require("../../qqmap-wx-jssdk.min.js"),
  n = getApp();

Page({

  /**
   * 页面的初始数据
   */
  data: {
    //tab框
    selected: 0,
    list: ['评估报告', '价格', '决策指标', '电气参数'],
    img: [],
    duration: 1000,
    current: 0,
    interval: 3e3,
    product: '',
    wxparse: [],
    listOne: [],
  },

  selected: function(e) {
    let that = this
    let index = e.currentTarget.dataset.index
    if (index == 0) {
      that.setData({
        selected: 0
      })
    } else if (index == 1) {
      that.setData({
        selected: 1
      })
    } else if (index == 2) {
      that.setData({
        selected: 2
      })
    } else {
      that.setData({
        selected: 3
      })
    }
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function(options) {

    console.log(options.productId)
    var data = {
      productId: options.productId
    }
    var that = this

    http.postReq(urlList.getProduct, data, function(res) {
      console.log(res.result)
      that.setData({
        product: res.result,
        img: res.result.imageList,
        listOne: res.result.decisions,
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


  swiperSlide: function(a) {
    this.setData({
      current: a.detail.current
    })
  },

  bigPic: function(a) {
    var imgurls = [];
    for (var i = 0; i < this.data.img.length; i++) {
      imgurls.push(this.data.img[i].imageUrl)
    }
    wx.previewImage({
      current: this.data.img[e],
      urls: imgurls,
      complete: function(a) {
        console.log(a);
      }
    }), console.log(this.data.img[e]);
  },

  first: function(that, data) {
    var rows = data; //data为查询的数据，自己根据自己情况自己生成就行
    for (var i = 0; i < rows.length; i++) {
      rows[i].CHCKED = true; //
    }
    that.setData({
      listOne: rows,
    });
  },

  clickLine: function(e) { //点击事件，进行显隐控制
    var that = this;
    var index = e.currentTarget.dataset.index;
    var decisionClassId = e.currentTarget.dataset.classid;

    var list = that.data.listOne;

    for (var i = 0; i < list.length; i++) {
      if (list[i].decisionClassId == decisionClassId) {
        if (list[i].hidden == true) {
          list[i].hidden = false;
        } else {
          list[i].hidden = true;
        }
      }
    }
    that.setData({
      listOne: list,

    })
  },

  question: function() {
    wx.switchTab({
      url: '../wenti/wenti',
    })
  },
  
  qingdan: function (e) {
    console.log(e.currentTarget.dataset.type)
    var that = this
    var data = {
      productId: e.currentTarget.dataset.id,
      type: e.currentTarget.dataset.type
    }
    http.postReq(urlList.focus, data, function (res) {
      var product = that.data.product
      if (e.currentTarget.dataset.type==1){
        product['focus'] = 1
      } 
      if (e.currentTarget.dataset.type == 2) {
        product['focus'] = null
      }
         that.setData({
           product: product
         })   
    }, true)
  },
  compare: function() {
    wx.navigateTo({
      url: '../compare/compare',
    })
  },

})