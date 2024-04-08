<script setup> 
    import { reactive, onMounted } from 'vue';
    import axios from 'axios';
    import FormInsert from '../reuse/FormInsert.vue';
    import router from '@/router';

    var objectInsertTest=reactive({
        
    });
    var responseInsertTest=reactive({});

    

    onMounted(() => {
        if(!localStorage.getItem("tokenLogin")) { router.push("/"); }
         
    });

    const handleSubmit = () => {
        try {
            const token=localStorage.getItem("tokenLogin");
            const headers = {
                Authorization: `Bearer ${token}`,
            };
            const url="http://localhost:8080/test/insertTest";
            const response=axios.post(url, objectInsertTest, { headers });
            response.then(reponse => {
                Object.assign(responseInsertTest, reponse.data);
                console.log(responseInsertTest);
            })
            .catch(errors => {
                console.log(errors);
            })
            router.push("/allTest/1");
        } catch(error) {
            console.log(error);
        }
    };
</script>
<template>
    <FormInsert title-props="Inserer une nouvelle test" :handle-submit="handleSubmit" submit-message="Inserer la  test">
        <input v-model="objectInsertTest.nomtest" type="text">
<input v-model="objectInsertTest.label" type="text">

    </FormInsert>
</template>
<style scoped>
    @import "@/assets/css/formulaire.css";
</style>
