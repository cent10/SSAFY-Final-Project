<template>
  <div class="tip-detail">
    <table class="table table-striped table-bordered table-hover mt-3">
      <thead>
        <tr>
          <th>제목</th>
          <th>{{notice.title}}</th>
        </tr>
      </thead>
      <tbody>
        <tr>
          <td>카테고리</td>
          <td style="white-space:pre;">{{notice.category}}</td>
        </tr>
        <tr>
          <td>작성자</td>
          <td style="white-space:pre;">{{notice.name}}</td>
        </tr>
        <tr>
          <td>날짜</td>
          <td style="white-space:pre;">{{notice.date.slice(0,10)}}</td>
        </tr>
        <tr>
          <td>내용</td>
          <td style="white-space:pre;">{{notice.content}}</td>
        </tr>
        <tr>
          <td>조회수</td>
          <td style="white-space:pre;">{{notice.hits}}</td>
        </tr>
      </tbody>
    </table>
    
    <!-- <b-button v-if="notice.user == user" class="mybutton1" @click="moveNoticeModify()">수정</b-button> -->
    <b-button v-if="notice.user == yol_uid" class="mybutton1" @click="moveNoticeModify()">수정</b-button>

    <!-- <b-button  v-if="notice.user == user" class="mybutton2 mx-3" @click="moveNoticeDelete()">삭제</b-button> -->
    <b-button v-if="notice.user == yol_uid" class="mybutton2 mx-3" @click="moveNoticeDelete()">삭제</b-button>

    <b-button class="border-0" @click="moveNotice()">목록으로</b-button>

    <div class="d-flex justify-content-between">
      <h4 class="my-3 text-left">댓글 <b-icon-chat-text></b-icon-chat-text></h4>
      <b-button size="sm" class="mybutton1 my-3" @click="mdAddCmtOpen()" v-if="this.$cookies.isKey('yol_token')">작성하기</b-button>
    </div>

    <b-list-group flush class="text-left">
        <b-list-group-item v-for="(onecomment,id) in this.comments" :key="id" class="px-3">
          <b-row>
            <b-col cols="9"><p class="m-0">{{ onecomment.content }} </p></b-col>
            <b-col cols="2"><p class="m-0">{{ onecomment.name }}</p></b-col>
            <b-col cols="2"><p class="m-0">{{ onecomment.date.slice(0,10) }}</p></b-col>
            <b-col cols="4">
              <b-icon-pencil v-if="onecomment.replier == yol_uid" class="mr-1" @click="mdModCmtOpen(onecomment.id)"></b-icon-pencil>
              <b-icon-trash  v-if="onecomment.replier == yol_uid" @click="delCmt(onecomment.id)"></b-icon-trash>
            </b-col>
          </b-row>
        </b-list-group-item>
      </b-list-group>

    <b-modal ref="mdAddCmt" hide-footer title="댓글 작성" >
      <b-form @submit="addCmt">
        <!-- <b-form-group label="작성자" label-for="f-a-c-content">
          <b-form-textarea
            id="f-a-c-content"
            v-model="replier"
            placeholder="작성자를 입력해주세요"
          ></b-form-textarea>
        </b-form-group> -->
        <!-- </b-form-group> 
            id="f-m-c-ontent" 
            v-model="replier" 
            placeholder="작성자를 입력해주세요"
          ></b-form-text> -->
        <b-form-group label="댓글" label-for="f-a-c-content">
          <b-form-textarea
            id="f-a-c-content"
            v-model="replycontent"
            placeholder="댓글을 입력해주세요"
            :rows="1"
            :max-rows="2"
          ></b-form-textarea>
        </b-form-group>

        <b-btn type="submit" variant="primary" class="float-right">댓글 쓰기</b-btn>
      </b-form>
    </b-modal>
    

    <b-modal ref="mdModCmt" hide-footer title="댓글 수정하기">
      <b-form @submit="modCmt">
        <b-form-group label="댓글" label-for="f-m-c-ontent">   
          <b-form-textarea
            id="f-m-c-ontent"
            v-model="commenter.content"
            placeholder="댓글을 입력해주세요"
            :rows="1"
            :max-rows="2"
          ></b-form-textarea>
        </b-form-group>

        <b-btn type="submit" variant="warning" class="float-right">댓글 수정</b-btn>
      </b-form>
    </b-modal>
  </div>
</template>

<script>

const API_URL = process.env.VUE_APP_SERVER_URL;

// import { mapGetters } from "vuex";
import axios from "axios";

export default {
  name: "TipDetail",
  data() {
    return {
      onecomment: {},
      cmt_id: "",
      comments: [],
      commenters: [],
      commenter: {},

      notice: {},
      datas: {},
      id: "",
      comment: "",
      title: "",
      replier: "",
      replycontent: "",
      yol_uid: this.$cookies.get('yol_uid'),
      
      name: "",
      
      time: "",
    
      content: "",
      
      categorys: [],
      notices: [],

      selectedCategory: null,


    };
  },
  components: {
    
  },
  computed: {
    // ...mapGetters(['userinfo', 'isLogin']),
  },
  created() {
    const uid = this.$cookies.get("yol_uid");
    console.log(uid);


    axios({
      method: "GET",
      url: `${API_URL}/tip/detail/` + this.$route.params.id ,

    //   headers: {
    //     Authorization: `Token ${token}`,
    //   },
    })
      .then(({ data }) => {
        console.log(data);
        this.notice = data.data;
        
      })
      .catch((err) => {
        console.log(err);
        alert("정보를 받아올때 에러가 발생했습니다.");
      });

    axios({
      method: "GET",
      url: `${API_URL}/tip/reply/all/` + this.$route.params.id ,

    })
      .then(({ data }) =>{
        console.log(data);
        this.comments=data.data;
      })

      
        // axios({
        // method:"GET",
        // url:`${API_URL}/rest-auth/user`,
        // headers:{
        //     'Authorization':`Token ${token}`,
        // }})
        // .then(res => {
        //     this.user = res.data
        //     })
        // .catch(err => {
        //     console.log(err)
        //     alert("정보를 받아올때 에러가 발생했습니다.");
        // });
  },
  methods: {
    swalSuccess(msg) {
      return this.$swal({
        icon: "success",
        title: "성공",
        text: msg,
        timer: 2000,
      });
    },
    swalError(msg) {
      return this.$swal({
        icon: "error",
        title: "에러",
        text: msg,
        timer: 2000,
      });
    },
    addCmt(evt) {
      evt.preventDefault();

      const uid = this.$cookies.get("yol_uid");

      axios({
        method: "POST",
        url:
          `${API_URL}/tip/reply/add`,
        data: {
          tip: this.$route.params.id,
          content: this.replycontent,
          replier: uid,
        },
        // headers: {
        //   Authorization: `Token ${token}`,
        // },
      })
        .then(({ data }) => {
          console.log(data);
          console.log(data.comments);
          this.comments = data.comments;
          console.log(this.comments);
          // this.datas=data;
          // console.log(this.datas)
          return this.swalSuccess("댓글 추가 완료");

          // if (data == 'success'){
          //   this.$router.push({path: `/articles_find/${data.id}`}); //리스트 전체목록
          // }
        })
        .then(() => {
          this.$refs.mdAddCmt.hide();
          location.reload();
          // this.$router.push({path: '/articles_find/'});
          // const path = `/articles_find/`+this.$route.params.pk
          // if ($route.path !== path) this.$route.push(path)
        })
        .catch((err) => {
          this.swalError(err.message);
        });
    },
    mdAddCmtOpen() {
      // this.formCmt.bd_id = v._id;
      // this.formCmt._id = '';
      // this.formCmt.id = '';
      // this.formCmt.content = '';
      this.$refs.mdAddCmt.show();
    },

    modCmt(evt) {
      evt.preventDefault();

      this.$swal({
        title: "댓글 수정",
        dangerMode: true,
        buttons: {
          cancel: {
            text: "취소",
            visible: false,
          },
          confirm: {
            text: "수정",
          },
        },
      })
        .then((data) => {
          if (!data) throw new Error("");

          // console.log(token);
          // console.log(333);
          // console.log(this.commenter.id);
          axios({
            method: "PUT",
            url:
              `${API_URL}/tip/reply/modify`,
            data: {
              content: this.commenter.content,
              id: this.commenter.id,
            },
            // headers: {
            //   Authorization: `Token ${token}`,
            // },
          })
            .then((res) => {
              console.log(res.data);
            })
            .catch((err) => {
              console.log(err);
              alert("수정할때 에러가 발생했습니다.");
            });
        })
        .then(() => {
          // if (!res.data.success) throw new Error(res.data.msg);
          return this.swalSuccess("댓글 수정 완료");
        })
        .then(() => {
          this.$refs.mdModCmt.hide();
          // this.$router.push({path:'/articles_find/'});
          location.reload();

          // this.refresh();
        })
        .catch((err) => {
          if (err.message) this.swalError(err.message);
          // else this.swalWarning('댓글 수정 취소');
        });
    },

    mdModCmtOpen(cmt_id) {
      // this.formCmt.bd_id = v._id;
      // this.formCmt._id = v._id;
      // this.formCmt.id = v.id;
      // this.formCmt.content = v.content;
      // const token = this.$cookies.get("auth-token");
      // console.log(token);

      axios({
        method: "GET",
        url:
         `${API_URL}/tip/reply/detail/` + cmt_id ,
        // headers: {
        //   Authorization: `Token ${token}`,
        // },
      })
        .then((res) => {
          console.log(res.data);
          this.commenter = res.data.data;
          // console.log(res.data.id);
          // this.find_pk = res.data.id;
        })
        .catch((err) => {
          console.log(err);
          alert("정보를 받아올때 에러가 발생했습니다.");
        });

      this.$refs.mdModCmt.show();
    },
    moveNotice() {
      this.$router.push({ path: "/tiplist/" });
    },
    moveNoticeModify() {
      this.$router.push({
        path: "/tipupdate/" + this.$route.params.id,
      });
    },
    moveNoticeDelete() {
    //   const token = this.$cookies.get("auth-token");
    //   console.log(token);
    //   console.log();
      axios({
        method: "DELETE",
        url: `${API_URL}/tip/delete/` + this.$route.params.id,
        // headers: {
        //   Authorization: `Token ${token}`,
        // },
      })
        .then(() => {
          this.moveNotice();
        })
        .catch((err) => {
          console.log(err);
          alert("삭제 에러가 발생했습니다.");
        });
    },
    delCmt(cmt_id) {
      this.$swal({
        title: "댓글 삭제",
        dangerMode: true,
        buttons: {
          cancel: {
            text: "취소",
            visible: true,
          },
          confirm: {
            text: "삭제",
          },
        },
      })
        .then(() => {
          // if (!res) throw new Error('');
          // const token = this.$cookies.get("auth-token");
          // console.log(token);
          // console.log(333);
          // console.log(this.commenter.id);
          axios({
            method: "DELETE",
            url:
              `${API_URL}/tip/reply/delete/` + cmt_id,

            // headers: {
            //   Authorization: `Token ${token}`,
            // },
          })
            .then((res) => {
              console.log(res.data);
            })
            .catch((err) => {
              console.log(err);
              alert("삭제 에러가 발생했습니다.");
            });
        })
        .then(() => {
          // if (!res.data.success) throw new Error(res.data.msg);
          return this.swalSuccess("댓글 삭제 완료");
        })
        .then(() => {
          this.$refs.mdModCmt.hide();
          // this.$router.push({path:'/articles_find/'});
          // this.refresh();
          location.reload();
        })
        .catch((err) => {
          if (err.message) this.swalError(err.message);
          // else this.swalWarning('댓글 수정 취소');
        });
    },

    // onCommentSubmit(comment) {

    // },
  },
};
</script>

<style scoped>
  .mybutton1 {
    background-color: #084481;
    border: none;
  }
  .mybutton2 {
    background-color: #fa1e44;
    border: none;
  }
  .tip-detail {
    padding-top: 100px;
    background-color: #F2F2F5;
    padding-left: 15%;
    padding-right: 15%;
    padding-bottom: 5%;
    margin-bottom: -30px;
  }
</style>