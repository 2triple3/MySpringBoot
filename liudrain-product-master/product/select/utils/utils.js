const app = getApp()
const urlList = require('../config.js')
var http = require('../utils/http.js');

const formatTime = date => {
  const year = date.getFullYear()
  const month = date.getMonth() + 1
  const day = date.getDate()
  const hour = date.getHours()
  const minute = date.getMinutes()
  const second = date.getSeconds()

  return [year, month, day].map(formatNumber).join('/') + ' ' + [hour, minute, second].map(formatNumber).join(':')
}

function getDateStr(today, addDayCount) {
  var dd;
  if (today) {
    dd = new Date(today);
  } else {
    dd = new Date();
  }
  dd.setDate(dd.getDate() + addDayCount); //获取AddDayCount天后的日期 
  var y = dd.getFullYear();
  var m = dd.getMonth() + 1; //获取当前月份的日期 
  var d = dd.getDate();
  if (m < 10) {
    m = '0' + m;
  };
  if (d < 10) {
    d = '0' + d;
  };
  return y + "-" + m + "-" + d;
}



const formatNumber = n => {
  n = n.toString()
  return n[1] ? n : '0' + n
}


function getLevel() {
  http.getReq(urlList.getUserInfo, function(res) {
    if (res.result.level == 0) {
      app.globalData.level = 0
      wx.showModal({
        title: '使用提示',
        content: '此功能需要付费使用',
        success(res) {
          if (res.confirm) {
            wx.navigateTo({
              url: '../pay/pay',
            })
          } else if (res.cancel) {
            console.log('用户点击取消')
            app.globalData.level = 0
          }
        }
      })
    } else {
      app.globalData.level = 1
    }
  });
  var level = app.globalData.level
  if (level == 1) {
    return true
  } else {
    return false
  }
}

const getStorage = (key) => {
  try {
    var v = wx.getStorageSync(key);
    return v;
  } catch (e) {
    return [];
  }
}
const setStorage = (key, cont) => {
  wx.setStorage({
    key: key,
    data: cont
  })
}

const wxPromisify = fn => {
  return function(obj = {}) {
    return new Promise((resolve, reject) => {
      obj.success = function(res) {
        resolve(res)
      }

      obj.fail = function(res) {
        reject(res)
      }

      fn(obj)
    })
  }
}

module.exports = {
  wxPromisify: wxPromisify,
  formatTime: formatTime,
  getLevel: getLevel,
  getStorage: getStorage,
  setStorage: setStorage,
  getDateStr: getDateStr,
}