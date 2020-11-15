<template>
  <div class="tip-list">
    <h2 @click="tipreturn()">팁 게시판</h2>
    <hr />
    <!-- <div class="d-flex justify-content-between"> -->
    <div align="left">
    <div >
      <div class="cate-gory">
      <select
        name="category"
        id="category"
        class="form-control"
        title="카테고리"
        v-model="selectedCategory"
        
      >
        <option value="null">카테고리</option>
        <option v-for="(onecategory,id) in categorys" :key="id" :value="onecategory.category">{{onecategory.category}}</option>
      </select>
      </div>
				<select class="custom-select" style="width:30%; display:inline-block;" v-model="key">
					<option value="1">제목+내용</option>
					<option value="2">제목</option>
					<option value="3">내용</option>					
				</select>				
				<input type="text" class="form-control" style="width:60%;margin-left:38px;display:inline-block;" v-model="word" />&nbsp;			
    <div class="search" >				
		</div><p/>
    </div>
    </div>

    <p />
    <p></p>
    <div align="right">
    <b-button class="border-0" @click="movewrite()" v-if="this.$cookies.isKey('yol_token')" style="background-color: #084481; margin-bottom: 20px;">글쓰기</b-button>
    </div>
    <table class="table table-bordered">
      <thead>
        <tr style="background-color: #bbbfca; border:1px solid white; font-weight: 700;">
          <th style="width:20%; border:1px solid #f4f4f2;">카테고리</th>
          <th style="width:40%; border:1px solid #f4f4f2;">제목</th>
          <th style="width:15%; border:1px solid #f4f4f2;">작성자</th>
          <th style="width:15%; border:1px solid #f4f4f2;">날짜</th>
          <th style="width:10%; border:1px solid #f4f4f2;" @click="orderNotice(1)">조회수</th>
        </tr>
      </thead>
      <tbody>
        <tr
          v-for="(notice,id) in notices"
          :key="id"
          v-show="keyWord(notice.title, notice.content, notice.category)" 
          style="background-color: #e8e8e8; border:1px solid white;"
        >
          <td style="border:1px solid #f4f4f2;">{{notice.category.slice(0, 6)}}</td>
          <td style="border:1px solid #f4f4f2;">
            <span v-if="notice.title.length > 25" @click="moveDetail(notice.id)" class="notice-title">{{notice.title.slice(0, 20) + "....."}}</span>
            <span v-else @click="moveDetail(notice.id)" class="notice-title">{{notice.title}}</span>
          </td>
          <td style="border:1px solid #f4f4f2;">{{notice.name}}</td>
          <td style="border:1px solid #f4f4f2;">{{notice.date.slice(0,10)}}</td>
          <td style="border:1px solid #f4f4f2;">{{notice.hits}}</td>

        </tr>
      </tbody>
    </table>
    <!-- </div> -->
    <p />
  </div>
</template>

<script>
/*jslint smarttabs:true */
// import sessionManager from "@/components/SessionManager.js";
const API_URL = process.env.VUE_APP_SERVER_URL;

// import { mapGetters } from "vuex";
import axios from "axios";

export default {
  name: "TipList",
  props: {
    word: {
      type: String,
      default: ""
    }
  },
  data() {
    return {
      key: "1",
      orderTitle: 0,
      id: "",

      s: "all",
      g: "all",

      title: "",
      
      no: "",

      categorys: [],
      notices: [],
      
      name: "",
      
      time: "",
  
      content: "",
      orderCount: 0,
      category: null,

      // category: "",
      
      selectedCategory: null,
    };
  },
  components: {},
  computed: {
    // ...mapGetters(['userinfo', 'isLogin']),
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
    
    axios({
      method: "GET",
      url: `${API_URL}/tip/all`,
      params: {
        page: 0,
      }
    })
      .then(({ data }) => {
        this.notices = data.data;
      })
      .catch((err) => {
        alert("정보를 받아올때 에러가 발생했습니다.");
        console.log(err);
      });
  },
  methods: {
    // getArticles() {
    //   var category = (this.selectedCategory == "null") ? null : this.selectedCategory
    
    //   const data =  {
    //     'category': category,
    //   }
    //   // const token = this.$cookies.get('auth-token')
    //   axios.post(`${API_URL}/tip/search/`, data)
    //   .then((res) => {
    //     this.notices = res.data
    //   })
    //   .catch((err) => {
    //     alert("정보를 받아올때 에러가 발생했습니다.");
    //     console.log(err)
    //   })
    // },
    moveDetail(id) {
      axios.get(`${API_URL}/tip/increase/`+ id)
      .catch((err) =>{
        console.log(err)
      })
      this.$router.push({ path: "/tipdetail/" + id });
    },
    movewrite(){
      this.$router.push({ path: "/tip/create/" });
    },
    
    keyWord(title,content,category) {      
        if(this.selectedCategory==null){    
          if(this.word == '') {
              return true;
          }
                  
          if(this.key == '1'){
              if(title.includes(this.word) || content.includes(this.word)){
                  return true;
              }
          }

          if(this.key == '2'){
              if(title.includes(this.word)){
                  return true;
              }
          }

          if(this.key == '3'){
              if(content.includes(this.word)){
                  return true;
              }
          }
        }

        if(this.selectedCategory == category){
          if(this.word == '') {
              return true;
          }
                  
          if(this.key == '1'){
              if(title.includes(this.word) || content.includes(this.word)){
                  return true;
              }
          }

          if(this.key == '2'){
              if(title.includes(this.word)){
                  return true;
              }
          }

          if(this.key == '3'){
              if(content.includes(this.word)){
                  return true;
              }
          }
        }

        return false;                
    },

    orderNotice(type){
      if(type==1){
        let comp1 = function (a,b){
            return a.hits - b.hits;
        }
        let comp2 = function (a,b){
            return b.hits - a.hits;
        }

        if(this.orderCount == 0){
            this.notices.sort(comp1);
        }else{
            this.notices.sort(comp2);
        }
        this.orderCount = (this.orderCount+1)%2;
      }
    },
    tipreturn(){
      location.reload();
    }
  },
  filter: {},
};
</script>
<style scoped>
  .tip-list {
    padding-top: 100px;
    background-color: #f4f4f2;
    padding-left: 15%;
    padding-right: 15%;
    padding-bottom: 5%;
    margin-bottom: -30px;

  }
  .cate-gory {
    padding-right: 5%;
  }
  .notice-title {
    cursor: pointer;
  }
  .notice-title:hover {
    text-decoration: underline;
  }

</style>