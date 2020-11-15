<template>
  <div>
  <div class="pay-info">
    <b-container fluid>
      <b-row class="pay-row">
        <div style="margin-top: 20px;">
          <h2>구매 정보</h2>
        </div>
      </b-row>
      <b-row align-h="end" class="pay-row">
        <div class="zullll"/>
      </b-row>
      <b-row align-h="center" class="pay-row">
        <b-col>
          <h5>예약 기간</h5>
          <div class="yol-date">
            <label for="start-date">시작일</label>
            <b-form-datepicker :date-disabled-fn="startDateDisabled" hide-header id="start-date" v-model="startDate"></b-form-datepicker>
          </div>
          <div class="yol-date">
            <label for="end-date">종료일</label>
            <b-form-datepicker :disabled="startDate === ''" :date-disabled-fn="endDateDisabled" hide-header id="end-date" v-model="endDate"></b-form-datepicker>
          </div>
        </b-col>
        <b-col>
          <h5>예약 상품</h5>
          <b-list-group style="margin-top: 30px;">
            <b-list-group-item v-for="(pl, i) in purchasingList" :key="i">
              <table width="100%">
                <colgroup>
                  <col width="20%">
                  <col width="80%">
                </colgroup>
                <tr>
                  <td rowspan="2">
                    +
                  </td>
                  <td class="product-info">
                    {{pl.name}}: {{pl.buyNum}}개
                  </td>
                </tr>
                <tr>
                  <td class="product-info">
                    {{pl.price * pl.buyNum}}원
                  </td>
                </tr>
              </table>
            </b-list-group-item>
          </b-list-group>
        </b-col>
      </b-row>
      <b-row align-h="end" class="pay-row">
        <div class="zullll"/>
      </b-row>
      <b-row align-h="end" class="pay-row">
        <div>
          <h1>총 {{totalPrice}}원</h1>
        </div>
      </b-row>
    </b-container>
  </div>
        <b-button @click="pay" style="margin-right: 0.5%;">카카오페이로 결제</b-button>
        <b-button @click="back" style="margin-left: 0.5%;">뒤로가기</b-button>
  </div>
</template>

<script>
const API_URL = process.env.VUE_APP_SERVER_URL;

import axios from 'axios';

const now = new Date();
const nowDate = now.getFullYear()+"-"+(now.getMonth()+1)+"-"+now.getDate();

export default {
  name: "Purchase",
  props: {
    pl: {
      type: Array,
    },
    value: {
    },
  },
  data() {
    return {
      isPurchasing: this.value,
      date: nowDate,
      context: null,
      purchasingList: this.pl,
      totalPrice: 0,
      startDate: "",
      endDate: "",
      productName: "",
    }
  },
  created() {
    window.scrollTo(0, 0);
    
    this.pl.map((p) => {
      this.totalPrice += p.price * p.buyNum;
    });
  },
  methods: {
    back() {
      this.$emit('input', false);
    },
    onContext(ctx) {
      this.context = ctx
    },
    startDateDisabled(ymd) {
      return ymd < nowDate;
    },
    endDateDisabled(ymd) {
      return ymd < this.startDate;
    },
    pay() {
      if(this.startDate===""){
        alert("날짜를 선택해주세요.");
        return;
      }
      
      const products = []; // 상품 아이디
      const nums = [];     // 상품 수량

      this.purchasingList.map((pl) => {
        products.push(pl.id);
        nums.push(pl.num);
      });

      axios({
        method: "POST",
        url: `${API_URL}/reservations`,
            data: {
              "start": this.startDate,
              "end": this.endDate,
              "amount": this.totalPrice,
              "user": this.$cookies.get('yol_uid'),
              "shop": this.purchasingList[0].shop,
              "products": products,
              "nums": nums,
            },
      }).then((res) => {
        this.productName += this.startDate + "~" +this.endDate + " " + this.purchasingList[0].name;
        if(this.purchasingList.length > 1){
          this.productName += " 외 " + (this.purchasingList.length-1);
        }

        axios({
          method: "POST",
            url: `${API_URL}/pay/kakao`,
            data: {
              "amount": this.totalPrice,
              "id": res.data.data,
              "name": this.productName,
              "quantity": 1,
              "shop": this.purchasingList[0].shop,
            }
          }).then((res) => {
            this.$cookies.set("yol_tid", res.data.data.tid);
            window.location.href = res.data.data.next_redirect_pc_url;
          }).catch((err) => {
            console.log(err);
          });
      }).catch((err) => {
        console.log(err);
      });

    }
  },
  watch: {
    startDate (to){
      this.endDate = to;
    },
  },
};
</script>

<style scoped>
.pay-info {
  margin: auto;
  width: 60%;
  background-color: #f4f4f2;
  margin-bottom: 15px;
}
.pay-row {
  padding: 3%;
}
.product-info {
  text-align: right;
}
.reserved-date {
  padding-top: 10%;
}
.yol-date {
  margin: 10% auto;
}
.zullll {
  background-color: #bbbfca;
  width: 100%;
  height: 2px;
}
label {
  display:block;
   text-align:left;
}
</style>
