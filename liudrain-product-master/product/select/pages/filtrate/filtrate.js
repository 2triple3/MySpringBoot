const urlList = require('../../config.js')
var http = require('../../utils/http.js');
var utils = require('../../utils/utils.js');
var app = getApp()
Page({

  /**
   * 页面的初始数据
   */
  data: {
    low: 0,
    heigh: 30000,
    brandhide: true,
    brandtext: '选择品牌',
    showbrand: 0,
    rate: null,
    brand: []
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

    var brandtext = app.globalData.brandtext
    var brand = app.globalData.brand
    var low = app.globalData.low
    var heigh = app.globalData.heigh
  
    var params = app.globalData.params
    var you = app.globalData.you
    var que = app.globalData.que
    this.setData({
      brand: brand,
      brandtext: brandtext,
      low: low,
      heigh: heigh,
      params: params,
      you: you,
      que: que
    })
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
    app.globalData.params = this.data.params
  },

  lowValueChangeAction: function(e) {
    this.setData({
      low: e.detail.lowValue
    })
  },

  heighValueChangeAction: function(e) {
    this.setData({
      heigh: e.detail.heighValue
    })
  },


  hideSlider: function(e) {
    this.selectComponent("#zy-slider").hide()
  },

  showSlider: function(e) {
    this.selectComponent("#zy-slider").show()
  },

  resetSlider: function(e) {
    this.selectComponent("#zy-slider").reset()
  },
  checkboxChange: function(e) {
    console.log(e)
    var type = e.currentTarget.dataset.id
    var that = this
    var brand = that.data.brand
    if (type == "brand") {
      if (e.detail.value.length == 0) {
        that.setData({
          brandtext: "选择品牌"
        })
      } else {

        for (var a = 0; a < brand.length; a++) {
          brand[a]["checked"] = false
          for (var b = 0; b < e.detail.value.length; b++) {
            if (brand[a]["pvName"] == e.detail.value[b]) {
              brand[a]["checked"] = true
            }
          }
        }


        that.setData({
          brandtext: e.detail.value,
          brand: brand
        })
      }
    }
    
    var params = that.data.params
    for (var i = 0; i < params.length; i++) {
      if (type == params[i]['pname']) {
        for (var j = 0; j < params[i]['paramsValues'].length; j++) {
          params[i]['paramsValues'][j]['checked'] = false
          for (var k = 0; k < e.detail.value.length; k++) {
            if (params[i]['paramsValues'][j]['pvName'] == e.detail.value[k]) {
              params[i]['paramsValues'][j]['checked'] = true
            }
          }
        }
      }
    }
    console.log(params)
    that.setData({
      params: params
    })
    console.log('checkbox发生change事件，携带value值为：', e.detail.value)
  },
  resetSelect: function() {
    this.resetSlider()
    var that = this
    http.getReq(urlList.getSelect, function(res) {
      that.setData({
        params: res.result.params,
        brand: res.result.brandList,
        low: 0,
        heigh: 30000,
        brandtext: "选择品牌",
      })
      app.globalData.brandtext = "选择品牌"
      app.globalData.low = 0
      app.globalData.heigh = 30000
      app.globalData.params = res.result.params
      app.globalData.isfiltrate = 0
    }, true)

  },
  brand: function() {
    var that = this
    var showbrand = that.data.showbrand
    if (showbrand == 0) {
      that.setData({
        showbrand: 1,
        brandhide: false,

      })
    } else {
      that.setData({
        showbrand: 0,
        brandhide: true,
      })
    }
  },
  filtrate: function() {
    app.globalData.runfiltrate = 1
    var that = this
    console.log(that.data)
    var brandtext = that.data.brandtext
    var brand = that.data.brand
    var low = that.data.low
    var heigh = that.data.heigh
    var params = that.data.params
    app.globalData.isfiltrate = 0

    if (brandtext != undefined || low != 0 || heigh != 30000 ) {
      if (brandtext != "选择品牌" || low != 0 || heigh != 30000 ) {
        app.globalData.isfiltrate = 1
      }
    }

    console.log(params)
    for (var i = 0; i < params.length; i++) {
      for (var j = 0; j < params[i]['paramsValues'].length; j++) {
        if (params[i]['paramsValues'][j]['checked']) {
          app.globalData.isfiltrate = 1
        }
      }
    }

    if (utils.getLevel()) {
      app.globalData.brand = brand
      app.globalData.brandtext = brandtext
      app.globalData.low = low
      app.globalData.heigh = heigh
      app.globalData.params = params
     wx.switchTab({
       url: '../xuanze/xuanze',
     })
    }




  },


})