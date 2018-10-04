package br.com.lucasfranco.digioTest

import br.com.lucasfranco.digioTest.interactor.MainInteractorInterface
import br.com.lucasfranco.digioTest.model.Cash
import br.com.lucasfranco.digioTest.model.Options
import br.com.lucasfranco.digioTest.presenter.MainPresenter
import br.com.lucasfranco.digioTest.view.MainActivityView
import io.reactivex.Single
import io.reactivex.schedulers.TestScheduler
import org.junit.Test
import org.junit.Before
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import io.reactivex.schedulers.Schedulers
import io.reactivex.android.plugins.RxAndroidPlugins
import java.lang.Exception


class ExampleUnitTest {

    @Mock lateinit var view : MainActivityView
    @Mock lateinit var interactor : MainInteractorInterface
    lateinit var testScheduler: TestScheduler
    lateinit var presenter: MainPresenter

    @Before
    fun setup(){
        MockitoAnnotations.initMocks(this)
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { _ -> Schedulers.trampoline() }
        testScheduler = TestScheduler()
        presenter  = MainPresenter(interactor, testScheduler, testScheduler)
    }

    @Test
    fun test() {
        //mock response
        val response = Options(listOf(), listOf(), Cash(" ", " "))
        //mock call
        Mockito.`when`(interactor.getOptions()).thenReturn(Single.just(response))
        //do request
        presenter.attachView(view)
        presenter.doRequestOptions()
        //do verifications
        Mockito.verify(view).showLoading()
        testScheduler.triggerActions()

        Mockito.verify(view).bindCash(response.cash)
        Mockito.verify(view).bindProducts(response.products)
        Mockito.verify(view).bindSpotlight(response.spotlights)
        testScheduler.triggerActions()
        Mockito.verify(view).hideLoading()
    }

    @Test
    fun testError() {
        //mock response
        val response = Options(listOf(), listOf(), Cash(" ", " "))
        //mock call
        Mockito.`when`(interactor.getOptions()).thenReturn(Single.error(Exception("")))
        //do request
        presenter.attachView(view)
        presenter.doRequestOptions()
        //do verifications
        Mockito.verify(view).showLoading()
        testScheduler.triggerActions()

        Mockito.verify(view).showToast("")
        testScheduler.triggerActions()
        Mockito.verify(view).hideLoading()
    }
}
