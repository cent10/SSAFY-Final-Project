<template>
    <div class="review-update" align="center">
    <h3 class="mt-2 mb-3">후기 수정</h3>
        <textarea type="text" class="form-control my-3" placeholder="내용을 작성해주세요!" v-model="review.content"></textarea>
 
            <div>
            <b-input-group>
            <b-input-group-prepend>
                <b-button @click="value = null">Clear</b-button>
            </b-input-group-prepend>
            <b-form-rating v-model="review.rate" color="#ff8800"></b-form-rating>
            <b-input-group-append>
                <b-input-group-text class="justify-content-center" style="min-width: 3em;">
                {{ value }}
                </b-input-group-text>
            </b-input-group-append>
            </b-input-group>
            </div>
            <div class="bu-tton">
            <b-button class="mybutton2 ml-2" @click="submit()">수정 완료</b-button>
            <b-button v-b-modal.modal-prevent-closing class="mybutton4 ml-2" @click="moveReviewDetail()">취소 하기</b-button>
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
            submit(){
                // const token = this.$cookies.get('auth-token')
                // console.log(token)

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

                ).then(({data}) => {
                    console.log(data)  
                    // console.log(data.id)                  
                    // if (data == 'success'){
                      this.$router.push({path: `/reviewdetail/${this.review.reservation}` }); 
                    // }
                }).catch((err) => {
                    console.log(this.review.id);
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
@import url('https://fonts.googleapis.com/css2?family=Jua&display=swap');


.review-update {
  padding-top: 100px;
  padding-left: 15%;
  padding-right: 15%;
  font-family: 'Jua', sans-serif;

}

</style>