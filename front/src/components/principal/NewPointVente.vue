<script setup> 
    import { reactive, onMounted } from 'vue';
    import axios from 'axios';
    import FormInsert from '../reuse/FormInsert.vue';
    import router from '@/router';

    var objectInsertPointVente=reactive({
        
    });
    var responseInsertPointVente=reactive({});

    

    onMounted(() => {
         
    });

    const handleSubmit = () => {
        try {
            const url="http://localhost:8080/pointVente/insertPointVente";
            const response=axios.post(url, objectInsertPointVente);
            response.then(reponse => {
                Object.assign(responseInsertPointVente, reponse.data);
                console.log(responseInsertPointVente);
            })
            .catch(errors => {
                console.log(errors);
            })
            router.push("/allPointVente");
        } catch(error) {
            console.log(error);
        }
    };
</script>
<template>
    <FormInsert title-props="Inserer une nouvelle pointVente" :handle-submit="handleSubmit" submit-message="Inserer la  pointVente">
        <input v-model="objectInsertPointVente.nomPointVente" type="text">

    </FormInsert>
</template>
<style scoped>
    @import "@/assets/css/formulaire.css";
</style>
