<template>
  <a-space direction="vertical" :style="{ width: '100%' }" :size="[0, 48]">
    <a-layout>
      <a-layout-header :style="headerStyle">
        <GlobalHeader />
      </a-layout-header>

      <a-layout-content :style="contentStyle">
        <router-view></router-view>
      </a-layout-content>
      <a-layout-footer :style="footerStyle">
        自动化测试平台
        <div class="privacy" :style="privacyStyle"><a>Privacy and Cookies</a> | <a>Terms and Conditions</a></div>
      </a-layout-footer>
    </a-layout>
  </a-space>


</template>
<script lang="ts" setup>
import type { CSSProperties } from 'vue';
//@ts-ignore
import GlobalHeader from '@/components/GlobalHeader.vue';
const headerStyle: CSSProperties = {
  textAlign: 'center',
  color: '#fff',
  height: 64,
  paddingInline: 50,
  lineHeight: '64px',
  backgroundColor: 'white',

};

const contentStyle: CSSProperties = {
  lineHeight: '120px',
  color: 'black',
  backgroundColor: 'white',
  margin: '48px',
};

const siderStyle: CSSProperties = {
  textAlign: 'center',
  lineHeight: '120px',
  color: '#fff',
  backgroundColor: '#3ba0e9',
};

const footerStyle: CSSProperties = {
  color: 'blue',
  backgroundColor: 'rgb(246 239 239)',
  padding: '16px',
  bottom: '0',
  position: 'fixed',
  left: 0,
  right: 0,
  display: 'flex',
  justifyContent: 'center',

};
const privacyStyle: CSSProperties = {
  padding: '16px',
  bottom: '0',
  position: 'fixed',
  left: 0,
  right: 0,
  display: 'flex',
  justifyContent: 'right',
  fontSize: '12px',
  zIndex: -1,

}
</script>
