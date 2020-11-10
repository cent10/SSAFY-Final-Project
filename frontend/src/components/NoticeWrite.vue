<template>
  <div class="notice-write">
    <h1 class="mt-2 mb-3">공지사항 작성 페이지</h1>
    <div class="d-none d-md-block">
      <div class="form-group">
          <div style="width: 45%;">
            <input type="text" class="form-control" placeholder="* 제목" v-model="title" />
          </div>
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
  name: "NoticeWrite",
  data() {
    return {

      title: "",
      user: "",
      content: "",
      
    };
  },
  created() {
    
  },

  components: {
    
  },
  computed: {
    // ...mapGetters(['isLogin']),
  },
  methods: {
    submit() {
    
      axios({
        method: "POST",
        url: `${API_URL}/notices`,
        data: {
          
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
          this.$router.push({ path: `/noticelist` }); //리스트 전체목록
          // }
        })
        .catch((err) => {
          console.log(err);
          alert("입력오류가 발생했습니다. \n 다시한번 확인해주세요!");
        });
    },
    moveNotice() {
      this.$router.push({ path: "/noticelist/" });
    },
  },
};
</script>

<style scoped>
.notice-write {
  padding-top: 100px;
  background-color: #F2F2F5;
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