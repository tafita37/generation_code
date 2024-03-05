<script setup>
    import { onMounted, reactive, getCurrentInstance  } from 'vue';
    import axios from 'axios';
    import TitleListe from '../reuse/TitleListe.vue';
    import ListeGenerale from '../reuse/ListeGenerale.vue';
    import H5TitleListe from '../reuse/H5TitleListe.vue';
    import SpanListe from '../reuse/SpanListe.vue';
    import ModifSupprButton from '../reuse/ModifSupprButton.vue';
    import router from '@/router';

    const allStockLaptopMagasin=reactive({});

    const getAllStockLaptopMagasin = async () => {
        try {
            const urlApiSpringBoot = 'http://localhost:8080/stockLaptopMagasin/allStockLaptopMagasin';
            const response = await axios.get(urlApiSpringBoot);
            Object.assign(allStockLaptopMagasin, response.data);
        } catch (error) {
            console.log(error.response);
        }
    };

    const loadFormModif = (idStockLaptopMagasin) => {
        router.push({path : "/modifStockLaptopMagasin", query: {idStockLaptopMagasin : idStockLaptopMagasin}});
    }

    const deleteStockLaptopMagasinById = (idStockLaptopMagasin) => {
        try {
            const urlApiSpringBoot = 'http://localhost:8080/stockLaptopMagasin/deleteStockLaptopMagasinById?idStockLaptopMagasin='+idStockLaptopMagasin;
            const response = axios.get(urlApiSpringBoot);
            getCurrentInstance().proxy.forceUpdate();
        } catch (error) {
            console.error('Erreur lors de l\'appel de l\'API', error);
        }
    }

    onMounted(getAllStockLaptopMagasin);
</script>
<template>
    <TitleListe title-liste="Liste des stockLaptopMagasin"/>
    <ListeGenerale>
        <div class="child_computer" v-for="(stockLaptopMagasin)  in allStockLaptopMagasin" :key="stockLaptopMagasin.idStockLaptopMagasin">
            <H5TitleListe labels="" :title-h5="stockLaptopMagasin.idStockLaptopMagasin"/>
            <SpanListe :title-span='stockLaptopMagasin.idStockLaptopMagasin' labels=''/><SpanListe :title-span='stockLaptopMagasin.idLaptop.label' labels=''/><SpanListe :title-span='stockLaptopMagasin.quantiteLaptopEntrant' labels=''/><SpanListe :title-span='stockLaptopMagasin.quantiteLaptopSortant' labels=''/><SpanListe :title-span='stockLaptopMagasin.dateMouvement' labels=''/><SpanListe :title-span='stockLaptopMagasin.prixAchat' labels=''/><SpanListe :title-span='stockLaptopMagasin.prixVente' labels=''/>
            <ModifSupprButton :funct-modif="() => loadFormModif(stockLaptopMagasin.idStockLaptopMagasin)" :funct-delete="() => deleteStockLaptopMagasinById(stockLaptopMagasin.idStockLaptopMagasin)"/>
        </div>
    </ListeGenerale>
</template>
<style scoped>
    @import "@/assets/css/liste.css";
</style>
