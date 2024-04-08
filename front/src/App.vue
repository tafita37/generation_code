<script setup>
  import { RouterLink, RouterView } from 'vue-router';
  import router from './router';

  const deconnexion = () => {
    localStorage.removeItem("tokenLogin");
    router.push("/");
  };
</script>

<template>
  <div class="sup">
    <ul class="header">
      <li><RouterLink to="/allTestss/1">Liste de Testss</RouterLink></li><li><RouterLink to="/allTest/1">Liste de Test</RouterLink></li><li><RouterLink to="/allLogin/1">Liste de Login</RouterLink></li>
      <li><RouterLink to="/newTestss">Insertion de testss</RouterLink></li><li><RouterLink to="/newTest">Insertion de test</RouterLink></li><li><RouterLink to="/newLogin">Insertion de login</RouterLink></li>
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
