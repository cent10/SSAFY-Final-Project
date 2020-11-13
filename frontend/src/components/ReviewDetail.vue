<template>
      <div class="review-detail" align="center">
    <h3 class="mt-2 mb-3">내가 쓴 후기</h3>
        <div class="review-content">
        {{review.content}}
 
            <div class="review-rate">
            
            <b-form-rating v-model="review.rate" readonly color="#ff8800"></b-form-rating>
            
            </div>
            <div class="bu-tton">
            <b-button class="mybutton2 ml-2" @click="moveUpdateReview()">수정하기</b-button>
            <b-button v-b-modal.modal-prevent-closing class="mybutton4 ml-2" @click="moveprofile()">마이프로필로</b-button>
            </div>
        </div>
  </div>
</template>

<script>
    const API_URL = process.env.VUE_APP_SERVER_URL
    
    import axios from "axios";
    // import { mapGetters } from "vuex";

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
        components:{
            
        },
        computed:{
            // ...mapGetters(['isLogin']),
        },
        created(){
            // var animal= '';
            // const token = this.$cookies.get('auth-token')
            // console.log(token)
            axios({
                    method: "GET"
                    ,url:`${API_URL}/reviews/`+this.$route.params.id,
                    
                  }

                ).then(res => {
                    this.review = res.data.data
                    // this.id=res.data.id
                    // console.log(res.data.id)
                    
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

<style>
@import url('https://fonts.googleapis.com/css2?family=Jua&display=swap');

.review-detail {
  padding-top: 100px;
  padding-left: 15%;
  padding-right: 15%;
}
.review-rate{
  padding-top: 50px;
}
.review-content{
  padding-top: 50px;
  font-family: 'Jua', sans-serif;
}
</style>