<template>
  <div class="review-write" align="center">
    <h3 class="mt-2 mb-3">후기 작성</h3>
        <b-textarea no-resize type="text" class="form-control my-3" placeholder="내용을 작성해주세요!" v-model="content" style="height: 300px;"></b-textarea>
            <div>
            <h5 style="text-align: left;">평점</h5>
            <b-input-group>
            <b-input-group-prepend>
                <b-button @click="value = null">Clear</b-button>
            </b-input-group-prepend>
            <b-form-rating v-model="value" color="#ff8800"></b-form-rating>
            <b-input-group-append>
                <b-input-group-text class="justify-content-center" style="min-width: 3em;">
                {{ value }}
                </b-input-group-text>
            </b-input-group-append>
            </b-input-group>
            </div>
            <div class="bu-tton">
            <b-button class="mybutton2 ml-2" @click="submit()" variant="danger">작성 완료</b-button>
            <b-button v-b-modal.modal-prevent-closing class="mybutton4 ml-2" @click="moveprofile()" style="background-color: #084481">취소 하기</b-button>
            </div>
  </div>
</template>

<script>
// import StarRating from "./StarRating.vue";

import axios from "axios";
const API_URL = process.env.VUE_APP_SERVER_URL;



export default {
  name: "WriteReview",

  data() {
    return {
        value: null,
        content: "",
    }
  },
  created(){
    window.scrollTo(0, 0);
  },
  methods:{
        submit(){
          if(this.content === null || this.content.length === 0){
            alert("내용을 입력해주세요!");
            return;
          }
          else if(this.value === null){
            alert("평점을 매겨주세요!");
            return;
          }
          axios({
            method: "POST",
            url: `${API_URL}/reviews`,
            data: {
            
            reservation: this.$route.params.id,
            rate: this.value,
            content: this.content,
            
            },
            
            })
            .then(() => {
              this.$router.push({ path: `/myprofile` }); //리스트 전체목록
            })
            .catch((err) => {
              console.log(err);
              alert("입력오류가 발생했습니다. \n 다시한번 확인해주세요!");
            });
        },
        moveprofile() {
            this.$router.push({ path: "/myprofile" });
        },
  },

}
</script>

<style>

.review-write {
  padding-top: 100px;
  padding-left: 30%;
  padding-right: 30%;
}

.bu-tton{
    padding-top: 50px;
}


</style>