<template>
  <div class="tip-update">
    
    <h1 class="mt-2 mb-3">팁 수정 페이지</h1>

    <div style="width:40%; margin:0px auto">	
		<div class="form-group">
        <input type="text" class="form-control" placeholder="* 제목" v-model="notice.title">
		</div>
		<div class="form-group">

             <select name="category" id="category" class="form-control" title="카테고리" v-model="notice.category" style="width:30%; float:left; margin-right:20px;">
				<option value="null">카테고리</option>			
                <option v-for='(onecategory,id) in categorys' :key="id" :value="onecategory.category" >{{onecategory.category}}</option>											    
			</select>			 
            <textarea class="form-control" rows="5" placeholder="내용" v-model="notice.content"></textarea>
             
		</div>		 
		<button type="button" class="btn btn-primary" style="width:100%; margin:10px auto;" @click="submit()">수정</button>
		<button type="button" class="btn btn-primary" style="width:100%; margin:10px auto;" @click="moveNoticeDetail()">취소</button>		
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
            // var animal= '';
            // const token = this.$cookies.get('auth-token')
            // console.log(token)
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
                    // console.log(res.data.id)
                    
                })
                 .catch(err => {
                     console.log(err)
                     alert('정보를 받아올때 에러가 발생했습니다.')

                });

          axios.get(`${API_URL}/category/all`)
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
        methods:{
            submit(){
                // const token = this.$cookies.get('auth-token')
                // console.log(token)

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

                ).then(({data}) => {
                    console.log(data)  
                    // console.log(data.id)                  
                    // if (data == 'success'){
                      this.$router.push({path: `/tipdetail/${this.notice.id}`}); 
                    // }
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

<style>
.tip-update {
  padding-top: 100px;
  background-color: #F2F2F5;
}
</style>