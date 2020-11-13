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
    <b-button class="border-0" @click="movewrite()" v-if="this.$cookies.isKey('yol_token')">글쓰기</b-button>
    </div>
    <table class="table table-striped table-bordered table-hover">
      <thead>
        <tr>
          <th style="width:25%;">제목</th>
          <th style="width:35%;">내용</th>
          <th style="width:25%;">날짜</th>
          <th style="width:15%;" @click="orderNotice(1)">조회수</th>
        </tr>
      </thead>
      <tbody>
        <tr
          v-for="(notice,id) in notices"
          :key="id"
          v-show="keyWord(notice.title, notice.content)"
          @click="moveDetail(notice.id)"
        >
          <td>{{notice.title.slice(0, 8)}}</td>
          <td>{{notice.content.slice(0, 7)}}</td>
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

      
      
    };
  },
  components: {},
  computed: {
    // ...mapGetters(['userinfo', 'isLogin']),
  },
  created() {
    
    axios
      .get(`${API_URL}/notices`)
      .then(({ data }) => {
        console.log(data)
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
  }
  
</style>