<template>
  <div class="tip-write">
    <h1 class="mt-2 mb-3">팁 작성 페이지</h1>
    <div class="d-none d-md-block">
      <div class="form-group">
        
        <div style="padding-left: 70%;">
          <select name="category" id="category" class="form-control my-2" title="카테고리" v-model="SelectedCategory" >
            <option value="null">카테고리</option>
            <option
              v-for="(onecategory,id) in this.categorys"
              :key="id"
              :value="onecategory.category"
            >{{onecategory.category}}</option>
          </select>
        </div>

          <div style="width: 100%;">
            <input type="text" class="form-control" placeholder="* 제목" v-model="title" />
          </div>
        
        <b-textarea no-resize type="text" class="form-control my-2" placeholder="내용" v-model="content" style="height: 400px;"/>
      </div>
      <b-button block class="mybutton2 mb-3" @click="submit()">등록</b-button>
      <b-button block class="mb-4" @click="moveNotice()">취소</b-button>
    </div>
  </div>

</template>

<script>
import axios from "axios";
const API_URL = process.env.VUE_APP_SERVER_URL;


export default {
  name: "TipWrite",
  data() {
    return {
      categorys: [],

      title: "",
      user: "",
      content: "",
      SelectedCategory: null,
      
    };
  },
  created() {
    window.scrollTo(0, 0);
    
    axios
      .get(`${API_URL}/category/all`)
      .then((res) => {
        this.categorys = res.data.data;
      })
      .catch((err) => {
        alert("정보를 받아올때 에러가 발생했습니다.");
        console.log(err);
      });
  },
  methods: {
    submit() {
      const uid = this.$cookies.get("yol_uid");

      if(this.SelectedCategory === null){
        alert("카테고리를 선택해주세요!");
        return;
      }
      else if(this.title === null || this.title.length === 0){
        alert("제목을 입력해주세요!");
        return;
      }
      else if(this.content === null || this.content.length === 0){
        alert("내용을 입력해주세요!");
        return;
      }

      axios({
        method: "POST",
        url: `${API_URL}/tip/create`,
        data: {
          
          category: this.SelectedCategory,
          
          title: this.title,
          user: uid,
          content: this.content,
          
        },
      })
        .then(() => {
          this.$router.push({ path: `/tiplist` }); //리스트 전체목록
        })
        .catch((err) => {
          console.log(err);
          alert("입력오류가 발생했습니다. \n 다시한번 확인해주세요!");
        });
    },
    moveNotice() {
      this.$router.push({ path: "/tiplist/" });
    },
  },
};
</script>

<style scoped>

.tip-write {
  padding-top: 100px;
  background-color: #F2F2F5;
  padding-left: 15%;
  padding-right: 15%;
  padding-bottom: 5%;
  margin-bottom: -30px;
}
.mybutton1 {
  background-color: #084481;
  border: none;
}
.mybutton2 {
  background-color: #fa1e44;
  border: none;
}
</style>