<template>
  <div>
    <h2>팁 게시판</h2>
    <hr />
    <div class="d-flex justify-content-between">
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

    <div class="search" align="left">				
				<select class="custom-select" style="width:15%; display:inline-block;" v-model="key">
					<option value="1">제목+내용</option>
					<option value="2">제목</option>
					<option value="3">내용</option>					
				</select>				
				<input type="text" class="form-control" style="width:70%;margin-left:5px;display:inline-block;" v-model="word" />&nbsp;			
		</div><p/>

    <p />
    <p></p>
    <b-button class="border-0" @click="movewrite()">글쓰기</b-button>

    <table class="table table-striped table-bordered table-hover">
      <thead>
        <tr>
          <th style="width:20%;">카테고리</th>
          <th style="width:20%;">제목</th>
          <th style="width:20%;">내용</th>
          <th style="width:15%;">작성자</th>
          <th style="width:15%;">날짜</th>
          <th style="width:10%;" @click="orderNotice(1)">조회수</th>
        </tr>
      </thead>
      <tbody>
        <tr
          v-for="(notice,id) in notices"
          :key="id"
          v-show="keyWord(notice.title, notice.content, notice.category)"
          @click="moveDetail(notice.id)"
        >
          <td>{{notice.category.slice(0, 6)}}</td>
          <td>{{notice.title.slice(0, 8)}}</td>
          <td>{{notice.content.slice(0, 7)}}</td>
          <td>{{notice.user}}</td>
          <td>{{notice.date.slice(0,10)}}</td>
          <td>{{notice.hits}}</td>

        </tr>
      </tbody>
    </table>
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
  data() {
    return {
      key: "1",
      word: "",
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

    
    axios
      .get(`${API_URL}/tip/all`)
      .then(({ data }) => {
        console.log(data)
        this.notices = data.data;
        //   console.log(this.data)
        //   this.comments = notices.comments;
        //   console.log(comments)
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
    }
  },
  filter: {},
};
</script>