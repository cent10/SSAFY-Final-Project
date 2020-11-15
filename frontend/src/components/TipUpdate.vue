<template>
  <div class="tip-update">
    <h1 class="mt-2 mb-3">팁 수정 페이지</h1>
    <div class="d-none d-md-block">
      <div class="form-group">
      
        <div style="padding-left: 70%;">
          <select name="category" id="category" class="form-control my-2" title="카테고리" v-model="notice.category">
            <option value="null">카테고리</option>
            <option
              v-for="(onecategory,id) in this.categorys"
              :key="id"
              :value="onecategory.category"
            >{{onecategory.category}}</option>
          </select>
        </div>

          <div style="width: 100%;">
            <input type="text" class="form-control" placeholder="* 제목" v-model="notice.title">
          </div>
        <b-textarea no-resize type="text" class="form-control my-2" placeholder="내용" v-model="notice.content" style="height: 400px;"/>
		</div>		 
		<b-button block class="mybutton2 mb-3" @click="submit()">수정</b-button>
		<b-button  block class="mybutton1 mb-4" @click="moveNoticeDetail()">취소</b-button>		
    </div>
  </div>
  
</template>

<script>
    const API_URL = process.env.VUE_APP_SERVER_URL
    
    import axios from "axios";
    // import { mapGetters } from "vuex";

    export default {
        name: "TipUpdate",
        data() {
            return {
              notice: {},
              categorys: [],

              title: '',

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
                    ,url:`${API_URL}/tip/detail/`+this.$route.params.id,
                    
                    // headers:{
                    //   'Authorization':`Token ${token}`
                    // },
                  }

                ).then(res => {
                    this.notice = res.data.data
                    // this.id=res.data.id
                    
                })
                 .catch(err => {
                     console.log(err)
                     alert('정보를 받아올때 에러가 발생했습니다.')

                });

          axios.get(`${API_URL}/category/all`)
              .then((res) => {
                this.categorys = res.data.data;
            })
            .catch((err) => {
                  alert("정보를 받아올때 에러가 발생했습니다.");
                  console.log(err);
            });
        
        },
        methods:{
            submit(){
                axios({
                    method: "PUT"
                    ,url:`${API_URL}/tip/modify`,
                    data: {   
                      title: this.notice.title,
                      content: this.notice.content,
                      category: this.notice.category,
                      id : this.notice.id,

                    },
                    // headers:{
                    //   'Authorization':`Token ${token}`
                    // },
                  }

                ).then(() => {
                    this.$router.push({path: `/tipdetail/${this.notice.id}`}); 
                }).catch((err) => {
                    alert('수정할때 에러가 발생했습니다. \n 입력값을 다시 한번 확인해주세요!')
                    console.log(err)
                });
            },
            moveNoticeDetail(){
                this.$router.push({path: `/tipdetail/${this.notice.id}`});
            },

        }
    }
</script>

<style scoped>
.tip-update {
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