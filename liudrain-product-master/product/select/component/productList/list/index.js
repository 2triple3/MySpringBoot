
Component({
  /**
   * 组件的属性列表
   */
  properties: {

  },

  /**
   * 组件的初始数据
   */
  data: {

  },

  /**
   * 组件的方法列表
   */
  methods: {
    toDetail: function () {
      console.log("详情处理")
      wx.navigateTo({
        url: '../productDetail/productDetail'
      })
    },
    focus: function () {
      console.log("关注和取消关注")
    },
  }
})