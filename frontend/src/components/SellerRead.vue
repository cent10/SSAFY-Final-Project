<template>
  <div class="seller-read">
    <h1 class="mt-2 mb-3" style="padding-bottom: 30px; font-weight: 700;">업체 정보</h1>
    <b-container v-if="shop !== null">
      <b-row style="text-align: left;">
        <b-col>
          <b-row align-v="center" align-h="center">
            <input ref="imageInput1" type="file" hidden @change="onChangeImage1" />
            <b-button v-if="isUpdating" type="button" @click="onClickImageUpload1">업체 이미지 업로드</b-button>
          </b-row>
          <b-row align-v="center" align-h="center" style="width: 100%; height: 200px; margin-top: 10px;">
            <b-img v-if="shopImgUrl" :src="shopImgUrl" style="max-width: 100%; max-height: 100%;"></b-img>
            <h5 v-else>업체 이미지 없음</h5>
          </b-row>
          업체명
          <b-input :readonly="!isUpdating" type="text" class="form-control my-2" placeholder="업체명" v-model="shop.shop.name" />
          전화번호
          <b-input :readonly="!isUpdating" type="text" class="form-control my-2" placeholder="전화번호 ex) 02-1234-5678" v-model="shop.shop.phone" />
          주소
          <b-input :readonly="!isUpdating" type="text" class="form-control my-2" placeholder="주소" v-model="shop.shop.address" />
          사업자등록번호
          <b-input :readonly="!isUpdating" type="text" class="form-control my-2" placeholder="사업자등록번호" v-model="shop.shop.number" />
          설명
          <b-textarea :readonly="!isUpdating" class="form-control my-2" placeholder="업체간단설명(50자 이내)" v-model="shop.shop.description" style="resize: none; height: 100px"/>
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
            <b-button v-if="isUpdating" type="button" @click="onClickImageUpload2">업체 설명 이미지 업로드</b-button>
          </b-row>
          <b-row align-v="center" align-h="center" style="width: 100%; height: 400px; margin-top: 10px;">
            <b-img v-if="shopDescImgUrl" :src="shopDescImgUrl" style="max-width: 100%; max-height: 100%;"></b-img>
            <h5 v-else>설명 이미지 없음</h5>
          </b-row>
        </b-col>
      </b-row>
      <b-row align-h="center" style="padding: 3%;">
        <div class="zullll"/>
      </b-row>
      <b-row style="padding-left: 5%; font-size: 30px; font-weight: 700;">
        상품관리
      </b-row>
      <b-row style="padding-top: 30px;">
        <b-col>
          <b-list-group>
            <b-list-group-item class="product-item" v-for="(product, i) in products" :key="i">
              <table>
                <colgroup>
                  <col width="40%">
                  <col width="20%">
                  <col width="20%">
                  <col width="20%">
                </colgroup>
                <tr>
                  <td v-if="product.stored">
                      {{product.name}}
                  </td>
                  <td v-else>
                    <b-input type="text" v-model="product.name" />
                  </td>
                  <td>
                    <b-input type="number" v-model="product.price" :readonly="product.stored" style="display: inline-block; width:80%;"/>
                      원
                  </td>
                  <td>
                    일회구매제한 <b-input type="number" v-model="product.num" :readonly="product.stored" style="margin: auto; text-align: right; width:80%;"/>
                  </td>
                  <td v-if="product.stored">
                    <b-button v-if="isUpdating" @click="deleteProduct(i)">삭제</b-button>
                  </td>
                  <td v-else>
                    <b-button v-if="isUpdating" @click="storeProduct(i)">저장</b-button>
                    <b-button v-if="isUpdating" @click="cancelProduct(i)">취소</b-button>
                  </td>
                </tr>
              </table>
            </b-list-group-item>
            <b-list-group-item class="add-list" v-if="isUpdating" @click="addProduct()">
                <b-icon icon="plus-circle"/> 추가
            </b-list-group-item>
          </b-list-group>
        </b-col>
      </b-row>
      <b-row align-h="center" style="padding: 3%;">
        <div class="zullll"/>
      </b-row>
      <b-row style="padding-left: 5%; font-size: 30px; font-weight: 700;">
        후기관리
      </b-row>
      <b-row style="padding-top: 30px;">
        <b-col>
          <b-list-group style="max-height: 500px; overflow: auto;">
            <b-list-group-item class="review-item" v-for="(review, i) in reviews" :key="i">
                <b-row align-h="start" class="review-item-user">
                  <b-col cols=1>
                  </b-col>
                  <b-col cols=5>
                    <b-row style="margin-left: 0; width:100%;">
                        <StarRating v-model="review.rate" :max-stars="5"/>
                    </b-row>
                    <b-row style="margin-left: 0; width:100%;">
                      <span @click="showDetail(i)" class="review-content">{{review.summaryCont}}</span>
                    </b-row>
                  </b-col>
                  <b-col cols=3>
                    {{review.date}}
                  </b-col>
                  <b-col cols=3 v-if="review.reply === undefined">
                    <b-button @click="openReply(i)">답글 달기</b-button>
                  </b-col>
                </b-row>
                <b-row v-if="review.reply !== undefined" class="review-item-seller">
                  <b-col cols=1 style="text-align: right;">
                    <b-icon icon="arrow-return-right"/>
                  </b-col >
                  <b-col cols=5 style="padding-right: 20px; word-break:break-all;">
                    {{review.reply.content}}
                  </b-col>
                  <b-col cols=3>
                    {{review.reply.date.slice(0,10)}}
                  </b-col>
                </b-row>
            </b-list-group-item>
          </b-list-group>
        </b-col>
      </b-row>
    </b-container>
    <div style="padding: 30px">
      <b-button v-if="!isUpdating" class="mx-2" @click="changeUpdateStatus()" style="background-color: #084481;">수정하기</b-button>
      <b-button v-else class="mx-2" @click="changeShop()" variant="danger">수정완료</b-button>
      <b-button class="mx-2" @click="moveMyprofile()" style="background-color: #084481;">내정보로</b-button>
    </div>

    <b-modal id="review-detail" title="업체 상세 리뷰" hide-footer>
      <div style="margin: auto; height: 500px; max-width: 400px; word-break:break-all;">
        <StarRating v-model="modalRate" :max-stars="5"/>
        <p>
          {{modalContent}}
        </p>
      </div>
    </b-modal>

    <b-modal id="reply-post" title="답글 달기" hide-footer>
      <div style="margin: auto; height: 400px; max-width: 400px; word-break:break-all;">
        <StarRating v-model="modalRate" :max-stars="5"/>
        <p>
          {{modalContent}}
        </p>
      </div>
      <div style="padding: 5px;">
        <b-textarea autofocus v-model="writingReplyContent" style="resize: none; height: 100px; margin-bottom: 10px"/>
        <b-button style="margin-left: 85%; background-color: #084481;" @click="writeReply">작성</b-button>
      </div>
    </b-modal>
  </div>
</template>

<script>
const API_URL = process.env.VUE_APP_SERVER_URL;

import axios from "axios";

import StarRating from "./StarRating.vue";

export default {
  name: "SellerRead",
  data() {
    return {
      shopId: 0,
      isUpdating: false,
      shop: null,
      shopImg: null,
      shopImgUrl: null,
      shopDescImg: null,
      shopDescImgUrl: null,
      products: [],
      reviews: [],
      modalRate: 0,
      modalContent: "",
      writingReplyReviewId: 0, // 답글을 달 리뷰 아이디
      writingReplyContent: "",
    };
  },
  components: {
    StarRating,
  },
  created() {
    window.scrollTo(0,0);

    axios({
      method: "GET",
      url: `${API_URL}/shops/find/${this.$cookies.get('yol_uid')}`,
    })
    .then(({data}) => {
      this.shopId  = data.data;

      // 업체 정보 불러오기
      axios({
        method: "GET",
        url: `${API_URL}/shops/${this.shopId}`,
      })
      .then(({ data }) => {
        this.shop = data.data;
        if(data.data.img !== null){
          this.shopImgUrl = "http://k3a210.p.ssafy.io/img/activityx_shop_img/" + data.data.shop.img;
        }
        if(data.data.imgDesc !== null){
          this.shopDescImgUrl = "http://k3a210.p.ssafy.io/img/activityx_shop_imgDesc/" + data.data.shop.imgDesc;
        }
      })
      .catch((err) => {
        console.log(err);
        alert("업체 정보를 받아올때 에러가 발생했습니다.");
      });

      // 상품 리스트 불러오기
      axios({
        method: "GET",
        url: `${API_URL}/shops/${this.shopId}/products`,
      }).then(({data})=>{
        this.products = data.data;
        this.products.map((p) => {
          p.stored = true;
        });
      }).catch((err) => {
          console.log(err);
          alert("상품 정보를 받아올때 에러가 발생했습니다.");
      });

      // 후기 리스트 불러오기
      axios({
        method: "GET",
        url: `${API_URL}/reviews/list/${this.shopId}`,
      }).then(({data})=>{
        this.reviews = data.data;

        this.reviews.map((r) => {
          r.date = r.date.slice(0,10);

          if(r.content.length > 18){
            r.summaryCont = r.content.slice(0, 15) + ".....";
          } else {
            r.summaryCont = r.content;
          }

          axios({
            method: "GET",
            url: `${API_URL}/reviews/${r.id}/replies/0`,
          })
          .then(({data}) => {
            if(data.status)
              r.reply = data.data;
            this.$forceUpdate();
          })
          .catch((err) => {
            console.log(err);
            alert("후기 답글 정보 받아올때 에러가 발생했습니다.");
          });

        });

      }).catch((err) => {
          console.log(err);
          alert("후기 정보를 받아올때 에러가 발생했습니다.");
      });

    })
    .catch((err) => {
      console.log(err);
      alert("업체 아이디를 받아올때 에러가 발생했습니다.");
    });

      
  },
  methods: {
    changeUpdateStatus(){
      this.isUpdating = true;
      window.scrollTo(0,0);
    },
    changeShop() {

      if(
        this.shop.name === "" ||
        this.shop.phone === "" ||
        this.shop.address === "" ||
        this.shop.number === "" ||
        this.shop.description === "" ||
        this.shopImgUrl === null ||
        this.shopDescImgUrl === null
        ){
        alert("입력하지 않은 정보가 있습니다.");
        return;
      }
      
      // 수정사항 저장
      axios({
        method: "PUT",
        url: `${API_URL}/shops/${this.shopId}`,
        data: {
          "address": this.shop.address,
          "admin": this.shop.admin,
          "classification": this.shop.classification,
          "description": this.shop.description,
          "name": this.shop.name,
          "number": this.shop.number,
          "phone": this.shop.phone,
          "region": this.shop.region
        },
      }).then(()=>{
        
          axios({
            method: "PUT",
            url: `${API_URL}/shops/${this.shopId}/fileupload/img`,
            data: this.shopImg,
            })
            .then(() => {

                axios({
                  method: "PUT",
                  url: `${API_URL}/shops/${this.shopId}/fileupload/imgdesc`,
                  data: this.shopDescImg,
                  })
                  .then(() => {
                    this.isUpdating = false;
                    window.scrollTo(0,0);
                    alert("저장되었습니다.");
                  })
                  .catch((err) => {
                    alert("업체 이미지를 저장하는 과정에서 에러가 발생했습니다.");
                    console.log(err);
                  });
                }
                
            )
            .catch((err) => {
              alert("업체 설명 이미지를 저장하는 과정에서 에러가 발생했습니다.");
              console.log(err);
            });
      }).catch((err) => {
          console.log(err);
          alert("상품 정보를 저장하는 도중 에러가 발생했습니다.");
      });
    },
    deleteProduct(i) {
      if(confirm("정말로 이 상품을 삭제하시겠습니까?")){
        axios({
          method: "DELETE",
          url: `${API_URL}/shops/0/products/${this.products[i].id}`,
        })
        .then(() => {
          axios({
            method: "GET",
            url: `${API_URL}/shops/${this.shopId}/products`,
          }).then(({data})=>{
            this.products = data.data;
            this.products.map((p) => {
              p.stored = true;
            });
          }).catch((err) => {
              console.log(err);
              alert("상품 정보를 받아올때 에러가 발생했습니다.");
          });
        })
        .catch((err) => {
          console.log(err);
          alert("상품 삭제 실패!");
        });
      }
    },
    storeProduct(i){
      axios({
        method: "POST",
        url: `${API_URL}/shops/${this.shopId}/products`,
        data: {
          "description": null,
          "name": this.products[i].name,
          "num": this.products[i].num,
          "price": this.products[i].price,
          "shop": this.shopId,
        }
      }).then(()=>{
          axios({
            method: "GET",
            url: `${API_URL}/shops/${this.shopId}/products`,
          }).then(({data})=>{
            this.products = data.data;
            this.products.map((p) => {
              p.stored = true;
            });
          }).catch((err) => {
              console.log(err);
              alert("상품 정보를 받아올때 에러가 발생했습니다.");
          });
      }).catch((err) => {
          console.log(err);
          alert("상품 정보를 저장하는 과정에서 에러가 발생했습니다.");
      });
    },
    cancelProduct(i){
      this.products.splice(i, 1);
    },
    addProduct() {
        this.products.push({
          name: "",
          price: 0,
          num: 0,
        });
    },
    showDetail(i) {
      this.modalRate = this.reviews[i].rate;
      this.modalContent = this.reviews[i].content;
      this.$bvModal.show('review-detail');
    },
    openReply(i){
      this.modalRate = this.reviews[i].rate;
      this.modalContent = this.reviews[i].content;
      this.writingReplyReviewId = this.reviews[i].id;
      this.writingReplyContent = "";
      this.$bvModal.show('reply-post');
    },
    writeReply(){
      axios({
        method: "POST",
        url: `${API_URL}/reviews/${this.writingReplyReviewId}/replies`,
        data: {
          "content": this.writingReplyContent,
          "replier": this.$cookies.get('yol_uid'),
          "review": this.writingReplyReviewId,
        }
      }).then(()=>{

       axios({
        method: "GET",
        url: `${API_URL}/reviews/list/${this.shopId}`,
      }).then(({data})=>{
        this.reviews = data.data;

        this.reviews.map((r) => {
          r.date = r.date.slice(0,10);

          if(r.content.length > 18){
            r.summaryCont = r.content.slice(0, 15) + ".....";
          } else {
            r.summaryCont = r.content;
          }

          axios({
            method: "GET",
            url: `${API_URL}/reviews/${r.id}/replies/0`,
          })
            .then(({data}) => {
              if(data.status)
                r.reply = data.data;
              this.$bvModal.hide('reply-post');
              this.$forceUpdate();
            })
            .catch((err) => {
              console.log(err);
              alert("후기 답글 정보 받아올때 에러가 발생했습니다.");
            });
          });
        }).catch((err) => {
            console.log(err);
            alert("후기 정보를 받아올때 에러가 발생했습니다.");
        });

      }).catch((err) => {
          console.log(err);
          alert("답글 정보를 저장하는 과정에서 에러가 발생했습니다.");
      });
    },
    moveMyprofile() {
      this.$router.push({ path: "/myprofile" });
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
    
  },
};
</script>

<style scoped>
input::-webkit-outer-spin-button,
input::-webkit-inner-spin-button {
  -webkit-appearance: none;
  margin: 0;
}
.seller-read {
    margin: auto;
    padding: 100px;
    width: 80%;
    background-color: #f4f4f2;
}
.product-item {
  height: 100px;
}
.review-item {
  height: 200px;
  padding: 0;
}
.review-item-user {
  overflow: auto;
  width: 100%;
  margin: 0;
  height: 80px;
  padding: 10px;
  text-align: left;
}
.review-item-seller {
  overflow: auto;
  width: 100%;
  margin: 0;
  height: 120px;
  padding: 10px;
  text-align: left;
  background-color: #e8e8e8;
}
.zullll {
  background-color: #bbbfca;
  width: 100%;
  height: 1px;
}
.add-list {
  background-color: #bbbfca;
  cursor: pointer;
  transition: all 0.5s;
}
.add-list:hover {
  background-color: #495464;
}
.review-content {
  padding: 0;
  transition: all 0.5s;
}
.review-content:hover {
  cursor: pointer;
  text-decoration: underline;
}
</style>