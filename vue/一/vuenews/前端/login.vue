<template>
  <div id="app" style="text-align: center">
    <p style="font-size: x-large; font-weight: 700;">新闻管理系统登录</p>
    账号：<label>
    <input type="text" v-model="userName" required="required">
  </label><br><br>
    密码：<label>
    <input type="password" v-model="password" required="required">
  </label><br>
    <br>
    <button type="button" @click="login">登录</button>
    <router-view></router-view>
  </div>
</template>

<script>
  export default {
    name: 'login',
    data: function () {
      return {
        userName: '',
        password: ''
      }
    },
    methods: {
      login () {
        if(this.userName === "" || this.password === "") {
          layer.msg('账号或密码不得为空！');
        } else {
          console.log("message");
          var self = this;
          axios.post('http://localhost:80/news/login', {
            userName: self.userName,
            password: self.password
          }).then(function (result) {
            self.userName = self.password = "";
            if (result.data < 0) {
              layer.msg('账号或密码错误！')
            } else {
              sessionStorage.setItem('uid', result.data);
              self.$router.replace('/newsList').catch(err => err);
            }
          })
        }
      }
    }
  }

</script>

<style scoped>

</style>
