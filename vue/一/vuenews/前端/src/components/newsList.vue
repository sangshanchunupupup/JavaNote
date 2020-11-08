<template>
  <div id="app" style="text-align: center">
    <p style="font-size: x-large; font-weight: 700;">新闻管理系统</p>
    新闻标题：<label>
    <input type="text" v-model.trim="title">
  </label>
    新闻状态：<label>
    <select v-model="status">
      <option value="">请选择状态</option>
      <option value="1">未审核</option>
      <option value="2">已审核</option>
    </select>
  </label>
    <button class="btn btn-primary" @click="updateTable">查询</button>
    <button @click="logOut">退出登录</button>
    <p>总页数：{{pages}} ----- 总条数：{{total}}</p>
    <table style="margin: auto" border="1" width="30%">
      <tr>
        <td>新闻id</td>
        <td>新闻标题</td>
        <td>新闻类型</td>
        <td>添加时间</td>
        <td>新闻状态</td>
        <td>操作</td>
      </tr>
      <tr v-for="news in list">
        <td>{{news.id}}</td>
        <td>{{news.title}}</td>
        <td>{{news.typeName}}</td>
        <td>{{news.addTime}}</td>
        <td>{{news.status|passStatus}}</td>
        <td>
          <button v-if="news.status !== 2" class="btn btn-primary" @click="del(news.id)">删除</button>
          <button v-if="news.status === 1" class="btn btn-primary" @click="check(news.id)">审核</button>
        </td>
      </tr>
    </table>
    <div>
      <ul>
        <a href="javascript:" @click="updateTableByPage(1)">首页</a>
        <a href="javascript:" v-if="hasPreviousPage" @click="updateTableByPage(prePage)">上一页</a>
        <a href="javascript:" v-if="hasNextPage" @click="updateTableByPage(nextPage)">下一页</a>
        <a href="javascript:" @click="updateTableByPage(pages)">尾页</a>
        <p>第{{pageNum}}页</p>
      </ul>
    </div>
  </div>
</template>

<script>
  export default {
    name: 'newsList',
    data() {
      return {
        title: '',
        status: '',
        list: null,
        layerObject: null,

        hasPreviousPage: false,
        hasNextPage: false,
        pages: 1,
        prePage: 1,
        nextPage: 1,
        total: 1,
        pageNum: 1
      }
    },
    beforeCreate() {
      if (sessionStorage.length <= 0) {
        alert("请登录");
        this.$router.push('/').catch(err => err);
      }
    },
    mounted() {
      this.updateTable()
    },
    methods: {
      updateTable() {
        var self = this;
        axios.post('http://localhost:80/news/selectByCondition', {
          title: self.title,
          status: self.status
        }).then(function (response) {
          console.log(response.data);
          self.list = response.data.list;
          self.title = self.status = '';

          self.hasPreviousPage = response.data.hasPreviousPage;
          self.hasNextPage = response.data.hasNextPage;
          self.pages = response.data.pages;
          self.prePage = response.data.prePage;
          self.nextPage = response.data.nextPage;
          self.total = response.data.total;
          self.pageNum = response.data.pageNum;
        })
      },
      updateTableByPage(pageNum) {
        var self = this;
        axios.post('http://localhost:80/news/selectByPage/' + pageNum, {
        }).then(function (response) {
          self.list = response.data.list;

          self.hasPreviousPage = response.data.hasPreviousPage;
          self.hasNextPage = response.data.hasNextPage;
          self.pages = response.data.pages;
          self.prePage = response.data.prePage;
          self.nextPage = response.data.nextPage;
          self.total = response.data.total;
          self.pageNum = response.data.pageNum;
        })
      },

      del(uid) {
        var self = this;
        layer.confirm('确认删除？', {
          btn: ['删除', '取消']
        }, function () {
          axios.post('http://localhost:80/news/del/' + uid).then(function () {
            layer.msg('删除成功', {
              time: 1000
            });
            self.updateTable()
          })
        })
      },
      logOut() {
        var self = this;
        layer.confirm('确认退出？', {
          btn: ['退出', '取消']
        }, function () {
          self.$router.push('/login').catch(err => err);
          sessionStorage.clear();
          layer.closeAll('dialog');
        })
      },
      check(id) {
        var self = this;
        var uid = sessionStorage.getItem("uid");
        layer.confirm('确认审核？', {
          btn: ['确定', '取消']
        }, function () {
          axios.post('http://localhost:80/news/check/' + uid + "/" + id).then(function () {
            layer.msg('审核成功', {
              time: 1000
            });
            self.updateTable()
          })
        })
      }
    },
    filters: {
      passStatus(value) {
        if (value === 1) {
          return '未审核';
        }
        if (value === 2) {
          return '已审核';
        }
      }
    }
  }
</script>

<style scoped>

</style>
