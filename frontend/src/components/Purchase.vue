<template>
  <b-container fluid>
    <b-row align-h="center">
      <h1>결제페이지</h1>
    </b-row>
    <b-row align-h="center">
      <div>
        <b-calendar block hide-header  v-model="date"></b-calendar>
      </div>
    </b-row>
    <b-row align-h="center">
      <div>
        <h3>구매 정보</h3>
        <h5>예약날짜: {{date}}</h5>
        <b-list-group>
          <b-list-group-item v-for="(pl, i) in purchasingList" :key="i">
            <table>
              <tr>
                <td>
                  {{pl.name}}
                </td>
                <td>
                  {{pl.buyNum}}개
                </td>
                <td>
                  {{pl.price * pl.buyNum}}원
                </td>
              </tr>
            </table>
          </b-list-group-item>
        </b-list-group>
      </div>
    </b-row>
    <b-row align-h="center">
      <div>
        총 {{totalPrice}}원
      </div>
    </b-row>
    <b-row align-h="center">
      <b-button>카카오페이로 결제</b-button>
      <b-button @click="back">뒤로가기</b-button>
    </b-row>
  </b-container>
</template>

<script>

export default {
  name: "Purchase",
  props: {
    pl: {
      type: Array,
    },
    value: {
    },
  },
  data: function () {
    const now = new Date();
    return {
      isPurchasing: this.value,
      date: now.getFullYear()+"-"+(now.getMonth()+1)+"-"+now.getDate(),
      context: null,
      purchasingList: this.pl,
      totalPrice: 0,
    }
  },
  created() {
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
    }
  }
};
</script>

<style scoped>
</style>
