<script setup>
  import { RouterLink, RouterView } from 'vue-router';
  import router from './router';

  const deconnexion = () => {
    localStorage.removeItem("token[classNameLoginMaj]");
    router.push("/");
  };
</script>

<template>
  <div class="sup">
    <ul class="header">
      [navbarLinks]
      [navbarLinksInsert]
      <li>
        <a @click="deconnexion">
          Deconnexion
        </a>
      </li>
    </ul>
  </div>
  <RouterView />
</template>

<style scoped>
  .header_info {
    background-color: #120851;
    width: 100%;
    margin: 0;
    color: white;
    line-height: 2.8;
    display: flex;
    align-items: center;
  }

  .header_info li {
    margin-left: 30px;
  }

  .sup {
    background-color: #120851;
  }

  .header {
    color: white;
    margin: 1px;
    width: 60%;
    top: 40px;
    display: flex;
    line-height: 3.8;
    align-items: center;
    justify-content: space-between;
  }

  ul {
    width: 100%;
  }

  li {
    list-style: none;
  }
</style>
