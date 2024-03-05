<script setup> 
    import { reactive, onMounted } from 'vue';
    import axios from 'axios';
    import FormInsert from '../reuse/FormInsert.vue';
    import router from '@/router';

    var objectInsertMagasinUser=reactive({
        
    });
    var responseInsertMagasinUser=reactive({});

    

    onMounted(() => {
         
    });

    const handleSubmit = () => {
        try {
            const url="http://localhost:8080/magasinUser/insertMagasinUser";
            const response=axios.post(url, objectInsertMagasinUser);
            response.then(reponse => {
                Object.assign(responseInsertMagasinUser, reponse.data);
                console.log(responseInsertMagasinUser);
            })
            .catch(errors => {
                console.log(errors);
            })
            router.push("/allMagasinUser");
        } catch(error) {
            console.log(error);
        }
    };
</script>
<template>
    <FormInsert title-props="Inserer une nouvelle magasinUser" :handle-submit="handleSubmit" submit-message="Inserer la  magasinUser">
        <input v-model="objectInsertMagasinUser.nomMagasinUser" type="text">
<input v-model="objectInsertMagasinUser.mdp" type="text">
<input v-model="objectInsertMagasinUser.role" type="text">

    </FormInsert>
</template>
<style scoped>
    @import "@/assets/css/formulaire.css";
</style>
