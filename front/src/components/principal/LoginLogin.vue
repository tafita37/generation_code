<script setup>
    import { reactive, ref, onMounted } from 'vue';
    import axios from 'axios';
    import FormInsert from '../reuse/FormInsert.vue';
    import router from '@/router';

    var reponseLogin=ref('');
    var sendLogin=reactive({});

    const handleSubmit = () => {
        try {
            const url="http://localhost:8080/login/loginLogin";
            const response=axios.post(url, sendLogin);
            response.then(reponse => {
                reponseLogin.value=reponse.data;
                localStorage.setItem("tokenLogin", reponseLogin.value);
                router.push("/newLogin");
            })
            .catch(errors => {
                alert(errors);
            })
        } catch(error) {
            console.log(error);
        }
    };
</script>
<template>
    <FormInsert title-props="Se connecter" :handle-submit="handleSubmit" submit-message="Se connecter">
        <input v-model="sendLogin.mail" type="text" placeholder="UserName">
        <input v-model="sendLogin.mdp" type="password" placeholder="Password">
    </FormInsert>
</template>
<style scoped>
    @import "@/assets/css/formulaire.css";
</style>
