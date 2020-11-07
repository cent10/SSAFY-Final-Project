<template>
  <div>
    <h3>결제페이지</h3>
    <div>
       <b-calendar v-model="calendar" @context="onContext"></b-calendar>
       <p>Value: <b>'{{ calendar }}'</b></p>
      <p >Context:</p>
      <pre>{{ context }}</pre>
    </div>
    <div>
      <h5>구매할 상품 정보</h5>
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
    <div>
      총 {{totalPrice}}원
    </div>
    <b-button>카카오페이로 결제</b-button>
    <b-button @click="back">뒤로가기</b-button>
  </div>
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
    return {
      isPurchasing: this.value,
      calendar: '',
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
