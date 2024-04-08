<script setup> 
    import { reactive, onMounted } from 'vue';
    import axios from 'axios';
    import FormInsert from '../reuse/FormInsert.vue';
    import router from '@/router';

    var objectInsertLogin=reactive({
        
    });
    var responseInsertLogin=reactive({});

    

    onMounted(() => {
        
         
    });

    const handleSubmit = () => {
        try {
            const token=localStorage.getItem("tokenLogin");
            const headers = {
                Authorization: `Bearer ${token}`,
            };
            const url="http://localhost:8080/login/insertLogin";
            const response=axios.post(url, objectInsertLogin, { headers });
            response.then(reponse => {
                Object.assign(responseInsertLogin, reponse.data);
                console.log(responseInsertLogin);
            })
            .catch(errors => {
                console.log(errors);
            })
            router.push("/allLogin/1");
        } catch(error) {
            console.log(error);
        }
    };
</script>
<template>
    <FormInsert title-props="Inserer une nouvelle login" :handle-submit="handleSubmit" submit-message="Inserer la  login">
        <input v-model="objectInsertLogin.mail" type="text">
<input v-model="objectInsertLogin.mdp" type="text">

    </FormInsert>
</template>
<style scoped>
    @import "@/assets/css/formulaire.css";
</style>
