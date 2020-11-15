<template>
      <div class="review-detail" align="center">
    <h3 class="mt-2 mb-3" style="font-weight: 700;">내가 쓴 후기</h3>
        <div class="review-content">
        <b-textarea disabled no-resize type="text" class="form-control my-3" v-model="review.content" style="height: 300px;"></b-textarea>
            <h5 style="text-align: left;">평점</h5>
            <div>
              <b-form-rating v-model="review.rate" readonly color="#ff8800"></b-form-rating>
            </div>
            <div class="bu-tton">
            <b-button class="mybutton2 ml-2" @click="moveUpdateReview()" variant="danger">수정하기</b-button>
            <b-button v-b-modal.modal-prevent-closing class="mybutton4 ml-2" @click="moveprofile()" style="background-color: #084481;">마이프로필로</b-button>
            </div>
        </div>
  </div>
</template>

<script>
    const API_URL = process.env.VUE_APP_SERVER_URL
    
    import axios from "axios";

    export default {
        name: "ReviewDetail",
        data() {
            return {
              review: {},
              id:'',
              value: null,

              content: '',

            }
        },
        created(){
            window.scrollTo(0,0);
            axios({
                    method: "GET"
                    ,url:`${API_URL}/reviews/`+this.$route.params.id,
                    
                  }

                ).then(res => {
                    this.review = res.data.data
                    
                })
                 .catch(err => {
                     console.log(err)
                     alert('정보를 받아올때 에러가 발생했습니다.')

                });
        
        },
        methods:{
            
            moveprofile(){
                this.$router.push({path: "/myprofile"});
            },
            moveUpdateReview(){
                this.$router.push({path: `/updatereview/${this.review.reservation}`});
            }

        }
    }
</script>

<style scoped>
.review-detail {
  padding-top: 100px;
  padding-left: 30%;
  padding-right: 30%;
}
.review-content{
  padding-top: 50px;
}
</style>