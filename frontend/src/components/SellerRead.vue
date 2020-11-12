<template>
  <div class="seller-read">
    <h1 class="mt-2 mb-3">업체 정보</h1>
    <b-container v-if="shop !== null">
      <b-row style="text-align: left;">
        <b-col>
          <b-row align-v="center" align-h="center">
            <input ref="imageInput1" type="file" hidden @change="onChangeImage1" />
            <b-button type="button" @click="onClickImageUpload1">업체 이미지 업로드</b-button>
          </b-row>
          <b-row align-v="center" align-h="center" style="width: 100%; height: 200px; margin-top: 10px;">
            <b-img v-if="shopImg" :src="shopImgUrl" style="max-width: 100%; max-height: 100%;"></b-img>
            <h5 v-else>이미지 없음</h5>
          </b-row>
          업체명
          <input type="text" class="form-control my-2" placeholder="업체명" v-model="shop.shop.name" />
          전화번호
          <input type="text" class="form-control my-2" placeholder="전화번호 ex) 02-1234-5678" v-model="shop.shop.phone" />
          주소
          <input type="text" class="form-control my-2" placeholder="주소" v-model="shop.shop.address" />
          사업자등록번호
          <input type="text" class="form-control my-2" placeholder="사업자등록번호" v-model="shop.shop.number" />
          설명
          <textarea class="form-control my-2" placeholder="업체간단설명(50자 이내)" v-model="shop.shop.description" style="resize: none; height: 100px"/>
        </b-col>
        <b-col>
          <h4 style="padding-top: 10px">제공 서비스 종류</h4>
          <div v-if="shop.shop.classification">장비</div>
          <div v-else>레저/액티비티</div>
          <h4 style="padding-top: 10px">지역</h4>
          <div>{{shop.shop.region}}</div>
          <h4 style="padding-top: 10px">카테고리</h4>
          <div>{{shop.category}}</div>

          <b-row align-v="center" align-h="center" style="margin-top: 20px;">
            <input ref="imageInput2" type="file" hidden @change="onChangeImage2" />
            <b-button type="button" @click="onClickImageUpload2">업체 설명 이미지 업로드</b-button>
          </b-row>
          <b-row align-v="center" align-h="center" style="width: 100%; height: 400px; margin-top: 10px;">
            <b-img v-if="shopDescImg" :src="shopDescImgUrl" style="max-width: 100%; max-height: 100%;"></b-img>
            <h5 v-else>이미지 없음</h5>
          </b-row>
        </b-col>
      </b-row>
      <b-row>
        <b-col>
          <h5>상품리스트</h5>
          <b-list-group>
            <b-list-group-item v-for="(product, i) in products" :key="i">
              <table>
                <colgroup>
                  <col width="30%">
                  <col width="30%">
                  <col width="20%">
                  <col width="20%">
                </colgroup>
                <tr>
                  <td>
                      {{product.name}}
                  </td>
                  <td>
                    <input type="number" v-model="product.price" />원
                  </td>
                  <td>
                    일일판매수량: <input type="number" v-model="product.num" />
                  </td>
                  <td>
                    <b-button>제거</b-button>
                  </td>
                </tr>
              </table>
            </b-list-group-item>
            <b-list-group-item>
              <b-button> 추가</b-button>
            </b-list-group-item>
          </b-list-group>
        </b-col>
      </b-row>
      <b-row>
        <b-col>
          <h5>후기 관리</h5>
          <b-list-group>
            <b-list-group-item>
              후기후기1
            </b-list-group-item>
            <b-list-group-item>
              후기후기2
            </b-list-group-item>
            <b-list-group-item>
              후기후기3
            </b-list-group-item>
            <b-list-group-item>
              후기후기4
            </b-list-group-item>
          </b-list-group>
        </b-col>
      </b-row>
    </b-container>
    <div style="padding: 30px">
      <!-- <b-button v-if="notice.user == user" class="mybutton1" @click="moveNoticeModify()">수정</b-button> -->
      <b-button @click="moveNoticeModify()">수정</b-button>

      <!-- <b-button  v-if="notice.user == user" class="mybutton2 mx-3" @click="moveNoticeDelete()">삭제</b-button> -->
      <b-button class="mx-3" @click="moveNoticeDelete()">삭제</b-button>

      <b-button @click="moveNotice()">내정보로</b-button>
    </div>
  </div>
</template>

<script>
const API_URL = process.env.VUE_APP_SERVER_URL;

import axios from "axios";

export default {
  name: "SellerRead",
  data() {
    return {
      shop: null,
      shopImg: null,
      shopImgUrl: null,
      shopDescImg: null,
      shopDescImgUrl: null,
      products: [],

    };
  },
  components: {
    
  },
  computed: {
    // ...mapGetters(['userinfo', 'isLogin']),
  },
  created() {
    // const token = this.$cookies.get("auth-token");
    // console.log(token);

    axios({
      method: "GET",
      // url: `${API_URL}/shops/` + this.$route.params.id ,
      url: `${API_URL}/shops/` + 1,
       
    //   headers: {
    //     Authorization: `Token ${token}`,
    //   },
    })
      .then(({ data }) => {
        this.shop = data.data;
      })
      .catch((err) => {
        console.log(err);
        alert("정보를 받아올때 에러가 발생했습니다.");
      });

      axios({
        method: "GET",
        // url: `${API_URL}/shops/` + this.$route.params.id + `/products`,
        url: `${API_URL}/shops/` + 1 + `/products`,
      }).then(({data})=>{
        this.products = data.data;
        this.products.map((p) => {p.buyNum = 0;});
      }).catch((err) => {
          console.log(err);
          alert("상품 정보를 받아올때 에러가 발생했습니다.");
      });
      
  },
  methods: {
    
    moveNotice() {
      this.$router.push({ path: "/noticelist/" });
    },
    moveNoticeModify() {
      this.$router.push({
        path: "/noticeupdate/" + this.$route.params.id,
      });
    },
    moveNoticeDelete() {
    //   const token = this.$cookies.get("auth-token");
    //   console.log(token);
    //   console.log();
      axios({
        method: "DELETE",
        url: `${API_URL}/notices/` + this.$route.params.id,
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
    onClickImageUpload1() {
      this.$refs.imageInput1.click();
    },
    onClickImageUpload2() {
      this.$refs.imageInput2.click();
    },
    onChangeImage1(e) {
      const file = e.target.files[0];
      const imgFormData = new FormData();
      imgFormData.append('file', file);
      this.shopImg = imgFormData;
      this.shopImgUrl = URL.createObjectURL(file);
    },
    onChangeImage2(e) {
      const file = e.target.files[0];
      const imgFormData = new FormData();
      imgFormData.append('file', file);
      this.shopDescImg = imgFormData;
      this.shopDescImgUrl = URL.createObjectURL(file);
    },
    aa() {
      
          // axios({
          //   method: "PUT",
          //   url: `${API_URL}/shops/${}/fileupload/img`,
          //   data: this.shopImg,
          //   })
          //   .then(() => {

          //       axios({
          //         method: "PUT",
          //         url: `${API_URL}/shops/${}/fileupload/imgdesc`,
          //         data: this.shopDescImg,
          //         })
          //         .then(() => {
          //         })
          //         .catch((err) => {
          //           alert("업체 이미지를 저장하는 과정에서 에러가 발생했습니다.");
          //           console.log(err);
          //         });
          //       }
                
          //   )
          //   .catch((err) => {
          //     alert("업체 설명 이미지를 저장하는 과정에서 에러가 발생했습니다.");
          //     console.log(err);
          //   });
    }
    
  },
};
</script>

<style scoped>

.seller-read {
    margin: 100px auto 20px auto;
    padding: 20px;
    width: 60%;
    background-color: #F2F2F5;
}
</style>