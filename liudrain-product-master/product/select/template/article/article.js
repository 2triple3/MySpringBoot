// template/article/article.js
Component({
  /**
   * 组件的属性列表
   */
  properties: {

  },
 
    
    toDetail: function (t) {
      var e = t.currentTarget.dataset.roomid;
      wx.navigateTo({
        url: "/pages/result/detail?rentid=" + e
      });
    },
  
})
