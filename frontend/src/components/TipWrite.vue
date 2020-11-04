<template>
  <div>
    <h1 class="mt-2 mb-3">팁 작성 페이지</h1>
    <div class="d-none d-md-block">
      <div class="form-group">
          <div style="width: 45%;">
            <input type="text" class="form-control" placeholder="* 제목" v-model="title" />
          </div>
        
        <select name="category" id="category" class="form-control my-2" title="카테고리" v-model="SelectedCategory">
          <option value="null">카테고리</option>
          <option
            v-for="(onecategory,id) in this.categorys"
            :key="id"
            :value="onecategory.category"
          >{{onecategory.category}}</option>
        </select>
        <input type="text" class="form-control my-2" placeholder="작성자" v-model="user" />
       
        <textarea type="text" class="form-control my-2" placeholder="내용" v-model="content"></textarea>
      </div>
      <b-button block class="mybutton2 mb-3" @click="submit()">등록</b-button>
      <b-button block class="mb-4" @click="moveNotice()">취소</b-button>
    </div>
  </div>

</template>

<script>
import axios from "axios";
// import { mapGetters } from "vuex";
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
    axios
      .get(`${API_URL}/category/all`)
      .then((res) => {
        console.log(res)
        console.log(res.data);
        console.log(res.data.data);
        this.categorys = res.data.data;
        console.log(this.categorys);
      })
      .catch((err) => {
        alert("정보를 받아올때 에러가 발생했습니다.");
        console.log(err);
      });
  },

  components: {
    
  },
  computed: {
    // ...mapGetters(['isLogin']),
  },
  methods: {
    submit() {
    //   const token = this.$cookies.get("auth-token");
    //   console.log(token);
    //   console.log();
      axios({
        method: "POST",
        url: `${API_URL}/tip/create`,
        data: {
          
          category: this.SelectedCategory,
          
          title: this.title,
          user: this.user,
          content: this.content,
          
        },
        // headers: {
        //   Authorization: `Token ${token}`,
        // },
      })
        .then(({ data }) => {
          console.log(data);
          console.log(data.id);
          // if (data == 'success'){
          this.$router.push({ path: `/tiplist` }); //리스트 전체목록
          // }
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
.mybutton1 {
  background-color: #084481;
  border: none;
}
.mybutton2 {
  background-color: #fa1e44;
  border: none;
}
</style>