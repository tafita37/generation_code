<template lang="">
  <div>
      <h1>Appel d'une API Spring Boot lors du chargement du composant</h1>
      <div v-if="reponseApi">
      <h2>Réponse de l'API :</h2>
      <pre>{{ reponseApi }}</pre>
      </div>
  </div>
</template>
<script>
    import axios from 'axios';
    import { ref } from 'vue';
    
    const name=ref('');

    export default {
        data() {
            return {
                reponseApi: null,
            };
        },
        mounted() {
            this.appelerApi();
        },
        methods: {
            appelerApi() {
                const urlApiSpringBoot = 'http://localhost:8080/test/hello';
                axios.get(urlApiSpringBoot)
                    .then(response => {
                        console.log(response.data);
                        this.reponseApi = response.data;
                    })
                    .catch(error => {
                        console.error('Erreur lors de l\'appel de l\'API', error);
                    });
            },
        },
    };
</script>