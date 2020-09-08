// pages/zhinan/zhinan.js
const urlList = require('../../config.js')
var http = require('../../utils/http.js');
var utils = require('../../utils/utils.js');
var app = getApp()

import * as wxSearch from '../../component/wxSearch/wxSearch';
import {
  getStorage,
  setStorage
} from '../../utils/utils';

Page({

  /**
   * 页面的初始数据
   */
  data: {
    level: app.globalData.level,

    tabData: {
      searchList: getStorage('searchList'),
      result: [],
      activeIndex: 0,
      sliderOffset: 0,
      sliderLeft: 0,
      searchIsHidden: true,
      searchAllShow: false,
      inputVal: ''
    },
    articles: [],
    limit: 5,
    page: 1, //当前页
    load: true,
    loading: false, //加载动画的显示
    ishave: true,
  },

  onLoad: function(options) {
    //初始渲染-读取storage的历史记录
    wxSearch.init(this)
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
  bindClearSearch: function() {
    wxSearch.updataLog(this, [])
  },
  bindGoSchool(e) {
    let val = e.currentTarget.dataset.item;
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
  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function() {
    var level = app.globalData.level;
    console.log(level)
    console.log(app.globalData)
    var that = this
    http.getReq(urlList.getHomeArticle, function(res) {
      console.log(res)
      that.setData({
        articles: res.result.records
      })
    }, true)

  },

  more: function(e) {
    var id = e.currentTarget.dataset.id;
    var url = e.currentTarget.dataset.url;
    var type = e.currentTarget.dataset.type;
    console.log("type:" + type)
    if (type == 2) {
      if (utils.getLevel()) {
        console.log(url)
        wx.navigateTo({
          url: '../article/article?id=' + id,
        })
      }
    } else {
      console.log("url"+url)
      wx.navigateTo({
        url: '../article/article?url=' + url,
      })
    }
  },
  question: function() {

    wx.switchTab({
      url: '../wenti/wenti',
    })
  },
  select: function() {
    wx.switchTab({
      url: '../xuanze/xuanze',
    })
  },
  selectTab: function(e) {
    console.log(e.currentTarget.dataset.id)
    var tab = e.currentTarget.dataset.id
    if (tab == 1) {
      wx.switchTab({
        url: '../xuanze/xuanze',
      })
    }
    if (tab == 2) {
      app.globalData.quedian=1
      wx.switchTab({
        url: '../xuanze/xuanze',
      })
    }
    if (tab == 3) {
      wx.switchTab({
        url: '../qingdan/qingdan',
      })
    }
  },
  onReachBottom: function() {

    var that = this
    var pageNo = that.data.page
    var data = {
      pageNo: pageNo + 1,
      size: 4
    }
    if (that.data.ishave) {
      http.postReq(urlList.getHomeArticle, data, function(res) {
        console.log()
        if (res.result.records.length != 0) {
          var content = that.data.articles.concat(res.result.records)
          that.setData({
            articles: content,
            page: pageNo + 1
          })
        } else {
          that.setData({
            ishave: false
          })
        }
      }, true)
    }

  },

  onShareAppMessage: function() {
    return {
      title: "善问-吸尘器选择利器",
      desc: "善问",
      path: "/pages/zhinan/zhinan"
    };
  },



})