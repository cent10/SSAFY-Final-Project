<template>
  <div class="notice-list">
    <h2 @click="noticereturn()">공지사항</h2>
    <hr />
    <div class="search" align="center">				
				<select class="custom-select" style="width:15%; display:inline-block;" v-model="key">
					<option value="1">제목+내용</option>
					<option value="2">제목</option>
					<option value="3">내용</option>					
				</select>				
				<input type="text" class="form-control" style="width:70%;margin-left:5px;display:inline-block;" v-model="word" />&nbsp;			
		</div><p/>

    <p />
    <p></p>
    <div align=right>
    <b-button class="border-0" @click="movewrite()" v-if="this.auth==1" style="margin: 20px; background-color: #084481;">글쓰기</b-button>
    </div>
    <table class="table table-bordered">
      <thead>
        <tr style="background-color: #bbbfca; border:1px solid white; font-weight: 700;">
          <th style="width:70%;">제목</th>
          <th style="width:20%;">날짜</th>
          <th style="width:10%;" @click="orderNotice(1)">조회수</th>
        </tr>
      </thead>
      <tbody>
        <tr
          v-for="(notice,id) in notices"
          :key="id"
          v-show="keyWord(notice.title, notice.content)"
          style="background-color: #e8e8e8; border:1px solid white;"
        >
          <td>
            <span v-if="notice.title.length > 25" @click="moveDetail(notice.id)" class="notice-title">{{notice.title.slice(0, 20) + "....."}}</span>
            <span v-else @click="moveDetail(notice.id)" class="notice-title">{{notice.title}}</span>
          </td>
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
  name: "NoticeList",
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

      notices: [],
      
      name: "",
      
      time: "",
  
      content: "",
      orderCount: 0,

      auth: null,
      
    };
  },
  components: {},
  computed: {
    // ...mapGetters(['userinfo', 'isLogin']),
  },
  created() {
    
    window.scrollTo(0,0);
    axios({
      method: "GET",
      url: `${API_URL}/user/authority/`+this.$cookies.get("yol_uid"),

    })
    .then(({data}) =>{
      this.auth=data.data;
    }).catch((err) => {
        console.log(err);
        alert("정보를 받아올때 에러가 발생했습니다.");
    });

    axios
      .get(`${API_URL}/notices`)
      .then(({ data }) => {
        this.notices = data.data;
        
      })
      .catch((err) => {
        alert("정보를 받아올때 에러가 발생했습니다.");
        console.log(err);
      });
  },
  methods: {
    
    moveDetail(id) {
      this.$router.push({ path: "/noticedetail/" + id });
    },
    movewrite(){
      this.$router.push({ path: "/notice/create/" });
    },
    
    keyWord(title,content) {      
        
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
    noticereturn(){
      location.reload();
    }
  },
  filter: {},
};
</script>
<style scoped>
  .notice-list {
    padding-top: 100px;
    background-color: #F2F2F5;
    padding-left: 15%;
    padding-right: 15%;
    padding-bottom: 5%;
    margin-bottom: -30px;
  }
  .notice-title{
    cursor: pointer;
  }
  .notice-title:hover{
    text-decoration: underline;
  }
  
</style>