<template>
    <div class="review-update" align="center">
    <h3 class="mt-2 mb-3" style="font-weight: 700; margin-bottom: 50px;">후기 수정</h3>
        <b-textarea no-resize type="text" class="form-control my-3" placeholder="내용을 작성해주세요!" v-model="review.content" style="height: 300px;"></b-textarea>
            <div>
            <h5 style="text-align: left;">평점</h5>
            <b-input-group>
            <b-form-rating v-model="review.rate" color="#ff8800"></b-form-rating>
            <b-input-group-append>
                <b-input-group-text class="justify-content-center" style="min-width: 3em;">
                {{ review.rate }}
                </b-input-group-text>
            </b-input-group-append>
            </b-input-group>
            </div>
            <div class="bu-tton">
            <b-button class="mybutton2 ml-2" @click="submit()" variant="danger">수정 완료</b-button>
            <b-button v-b-modal.modal-prevent-closing class="mybutton4 ml-2" @click="moveReviewDetail()" style="background-color: #084481">취소 하기</b-button>
            </div>
  </div>
</template>

<script>
    const API_URL = process.env.VUE_APP_SERVER_URL
    
    import axios from "axios";
    // import { mapGetters } from "vuex";

    export default {
        name: "UpdateReview",
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
            window.scrollTo(0, 0);

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
            submit(){
                axios({
                    method: "PUT",
                    url:`${API_URL}/reviews/`+this.review.id,
                    data: {   
                      content: this.review.content,
                      id : this.review.id,
                      rate: this.review.rate,
                      reservation: this.review.reservation,
                    },
                    
                  }

                ).then(() => {
                    this.$router.push({path: `/reviewdetail/${this.review.reservation}` }); 
                }).catch((err) => {
                    alert('수정할때 에러가 발생했습니다. \n 입력값을 다시 한번 확인해주세요!')
                    console.log(err)
                });
            },
            moveReviewDetail(){
                this.$router.push({path: `/reviewdetail/${this.review.reservation}`});
            },

        }
    }
</script>

<style>

.review-update {
  padding-top: 100px;
  padding-left: 30%;
  padding-right: 30%;
}

</style>