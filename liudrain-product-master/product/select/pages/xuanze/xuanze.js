const urlList = require('../../config.js')
var http = require('../../utils/http.js');
var utils = require('../../utils/utils.js');
import * as wxSearch from '../../component/wxSearch/wxSearch';
import {
  getStorage,
  setStorage
} from '../../utils/utils';
var app = getApp()
Page({
  /**
   * 页面的初始数据
   */
  data: {
    tabData: {
      searchList: getStorage('searchList'),
      result: [],
      activeIndex: 0,
      sliderOffset: 0,
      sliderLeft: 0,
      searchIsHidden: true,
      searchAllShow: false,
      inputVal: '',
      focus: false,

    },
    pageNum: 1,
    productList: [],
    nLocation: 1,
    statusTip: false,
    notone: false,
    showfilterindex: 0,
    showtext: 0,
    index: null,
    sortindex: 0,
    logicSort: 0,
    selectindex: 0,
    sort: [],
    sorttext: "排序",
    isfiltrate: 0,

    isover: true,
    level: 0,
    temp: false
  },
  onLoad: function(options) {
    var that = this
    wxSearch.init(this)
    //初始渲染-读取storage的历史记录
    var querykey = app.globalData.querykey



    if (querykey) {
      wxSearch.showlog(this)
      wxSearch._setData(that, {
        focus: true
      })
      app.globalData.querykey = false
    }
    var querykey = app.globalData.querykey
    var that = this
    var focus = that.data.tabData["focus"]
    if (querykey) {
      wxSearch.showlog(this)
      that.setData({
        focus: true
      })
      app.globalData.querykey = false
      console.log(that.data.tabData)
    }
    var that = this
    var isfiltrate = app.globalData.isfiltrate
    var level = app.globalData.level
    var sorts = app.globalData.sorts
    that.setData({
      level: level,
      sort: sorts
    })
    console.log("level-------" + level)
    if (isfiltrate) {
      that.setData({
        isover: true
      })
    }
    that.getProductList(0)
  },
  onShow: function() {
    console.log("这是onShow返回的方法")
    var querykey = app.globalData.querykey
    var that = this
    var focus = that.data.tabData["focus"]
    if (querykey) {
      wxSearch.showlog(this)
      that.setData({
        focus: true
      })
      app.globalData.querykey = false
      console.log(that.data.tabData)
    }
    var that = this
    var isfiltrate = app.globalData.isfiltrate
    var level = app.globalData.level
    var runfiltrate = app.globalData.runfiltrate
    that.setData({
      level: level
    })
    if (isfiltrate) {
      that.setData({
        isover: true,
        pageNum: 1
      })
    }
    console.log("runfiltrate" + runfiltrate)
    if (1 == runfiltrate) {
      that.getProductList(0)
    }
  },


  bindSearchAllShow: function(e) {
    wxSearch.bindSearchAllShow(e, this)
  },
  bindInputSchool: function(e) {
    wxSearch.bindInputSchool(e, this)
  },
  bindGoSearch: function(e) {
    wxSearch.bindGoSearch(e, this)
  },
  bindconfirm: function(e) {
    wxSearch.bindconfirm(e, this)
  },
  bindClearSearch: function() {
    wxSearch.updataLog(this, [])
  },
  bindGoSchool(e) {
    var that = this
    let val = e.currentTarget.dataset.item;
    console.log(val)
    wxSearch._setData(that, {
      inputVal: val
    })
    wxSearch.goSchool(this, val)

  },
  bindDelLog(e) {
    wxSearch.bindDelLog(e, this)
  },
  bindShowLog(e) {
    wxSearch.bindShowLog(e, this)
  },
  bindHideLog(e) {
    wxSearch.bindHideLog(e, this)
  },
  bindSearchHidden() {
    wxSearch.bindSearchHidden(this)
  },

  select: function() {
    if (utils.getLevel()) {
      wx.showToast({
        title: '可以使用！',
      })
    }
  },
  toDetail: function(e) {
    var productId = e.currentTarget.dataset.id

    if (utils.getLevel()) {
      wx.navigateTo({
        url: '../productDetail/productDetail?productId=' + productId
      })
    }
  },
  focus: function(e) {
    console.log("关注和取消关注")
    console.log(e.currentTarget.dataset.type)

    var id = e.currentTarget.dataset.id
    var type = e.currentTarget.dataset.type

    var that = this
    var data = {
      productId: id,
      type: type
    }
    var productList = that.data.productList
    http.postReq(urlList.focus, data, function(res) {
      console.log(res)

      for (var i = 0; i < productList.length; i++) {
        if (id == productList[i]['productId']) {
          if (type == 1) {
            productList[i]['focus'] = 1
          }
          if (type == 2) {
            productList[i]['focus'] = null
          }
        }
      }
      that.setData({
        productList: productList
      })
    }, true)
  },

  getProductList: function(a) {
    var key = app.globalData.querykey
    var that = this
    var isfiltrate = app.globalData.isfiltrate
    console.log("isfiltrate:" + isfiltrate)
    that.setData({
      isfiltrate: isfiltrate,
      isover: true,
    })
    var pageNum = that.data.pageNum

    if (a == 0) {
      var pageNum = 1
      that.setData({
        pageNum: 1
      })
    } else if (a == -1) {
      var pageNum = 1
      that.setData({
        pageNum: 1
      })
    } else {
      var pageNum = that.data.pageNum + 1
      that.setData({
        pageNum: pageNum
      })
    }

    console.log("a的参数:" + a)
    console.log("pageNum:" + pageNum)
    var logicSort = that.data.logicSort

    var brandtext = app.globalData.brandtext
    //价格
    var heigh = app.globalData.heigh
    var low = app.globalData.low
    //重量
    var heigh1 = app.globalData.heigh1
    var low1 = app.globalData.low1

    var quedian = app.globalData.quedian


    if (quedian == 1) {
      var data = {
        type: 1
      }
      http.postReq(urlList.getSelect, data, function(res) {
        app.globalData.params = res.result.params
        console.log("12345")
        console.log(res.result.params)
        app.globalData.quedian = 1
        that.setData({
          isfiltrate: 1
        })
        app.globalData.params = res.result.params
      }, true)
    } 

    var params = app.globalData.params
    console.log("params的参数")
    console.log(params)
    var pingji = []
    var queryParams = []
    for (var i = 0; i < params.length; i++) {
      if (params[i]['pname'] == '评级') {
        for (var j = 0; j < params[i]['paramsValues'].length; j++) {
          if (params[i]['paramsValues'][j]['checked']) {
            pingji.push(params[i]['paramsValues'][j]['pvName'])
          }
        }
      } else {
        for (var k = 0; k < params[i]['paramsValues'].length; k++) {
          if (params[i]['paramsValues'][k]['checked']) {
            queryParams.push(params[i]['paramsValues'][k]['pvName'])
          }
        }
      }
    }
    console.log("queryParamsqueryParamsqueryParams")
    console.log(queryParams)

    var data = {
      quedian: quedian,
      params: queryParams,
      brandtext: brandtext,
      pingji: pingji,
      heigh: heigh,
      low: low,
      heigh1: heigh1,
      low1: low1,
      logicSort: logicSort,
      pageNum: pageNum,
      key: key
    }
    console.log(data)
    http.postReq(urlList.getProductList, data, function(res) {
      if (quedian == 1) {
        app.globalData.quedian = 0
      }
      console.log(res)
      if (res.status == 1 && res.result != null) {
        if (a == 1) {
          var list = that.data.productList.concat(res.result.list)
        } else {
          var list = res.result.list
        }
        that.setData({
          productList: list,
          notone: false,
        })
      } else if (res.status == 1 && res.result == null) {
        that.setData({
          isover: false,
          inputVal: key
        })
      } else {
        that.setData({
          notone: true,
          productList: [],
          isover: true
        })
      }
    }, true)
  },

  setFilterPanelResult: function(t, a) {
    var e = this;

    var show = e.data.showfilterindex
    console.log(show)
    if (show == 0) {
      e.setData({
        showfilterindex: 1

      })
    } else {
      e.setData({
        showfilterindex: 0
      })
    }


  },
  sortSelect: function(t) {
    // wx.showToast({
    //   title: "加载中...",
    //   icon: "loading",
    //   duration: 1e4
    // })
    var a = this,
      e = t.currentTarget.dataset.index;
    console.log(e)
    a.setData({
      isover: true,
      showfilter: !1,
      showfilterindex: 0,
      pageNum: 1
    })

    var sorts = app.globalData.sorts
    for (var i = 0; i < sorts.length; i++) {
      if (sorts[i]['sortId'] == e) {
        a.setData({
          sorttext: sorts[i]['showName'],
          sortindex: e != 0 ? 1 : 0,
          logicSort: e
        })
      }
    }

    a.getProductList(0);
  },

  selectFilter: function() {
    wx.navigateTo({
      url: '../filtrate/filtrate',
    })
  },
  onReachBottom: function() {
    var that = this

    console.log(that.data.isover)
    that.getProductList(1)
  },
})