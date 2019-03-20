package br.com.lucasfranco.digioTest

import br.com.lucasfranco.digioTest.interactor.MainInteractor
import br.com.lucasfranco.digioTest.model.Options
import br.com.lucasfranco.digioTest.model.Spotlight
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

class MainPresenterUnitTest {

    @Mock lateinit var view : MainActivityView
    @Mock lateinit var interactor : MainInteractor
    private lateinit var testScheduler: TestScheduler
    private lateinit var presenter: MainPresenter


    @Before
    fun setup(){
        //Init mocks and Schedulers
        MockitoAnnotations.initMocks(this)
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { _ -> Schedulers.trampoline() }
        testScheduler = TestScheduler()
        presenter  = MainPresenter(interactor, testScheduler, testScheduler)
    }

    @Test
    fun test() {
        //Mocking the response
        val response = Options(listOf(), listOf(), Spotlight(" ", " "))
        //Mocking the call from server, returning the mocked response
        Mockito.`when`(interactor.getOptions()).thenReturn(Single.just(response))
        //Actually call the method being tested
        presenter.attachView(view)
        presenter.doRequestOptions()
        //Do the verifications for what have to be called
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
        Mockito.`when`(interactor.getOptions()).thenReturn(Single.error(Exception("")))
        presenter.attachView(view)
        presenter.doRequestOptions()
        Mockito.verify(view).showLoading()
        testScheduler.triggerActions()
        Mockito.verify(view).showToast("")
        testScheduler.triggerActions()
        Mockito.verify(view).hideLoading()
    }
}
