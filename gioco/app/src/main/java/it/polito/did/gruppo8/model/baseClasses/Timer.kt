package it.polito.did.gruppo8.model.baseClasses

import android.os.CountDownTimer
import android.util.Log

class Timer(
    private var seconds: Int
)
{
    private var _currentTimeMillis: Long = 0
    var secondsRemaining: Int = (_currentTimeMillis/1000).toInt()
        private set

    private lateinit var _timer: CountDownTimer
    var isRunning: Boolean = false
        private set

    /**
     * Sets countdown time in seconds if timer is not already running.
     *
     * @param seconds Countdown time in seconds.
     */
    fun setTime(seconds: Int){
        if(isRunning){
            Log.d("Timer", "Can't change countdown time if timer is running.")
            return
        }
        this.seconds = seconds
    }

    /**
     * Starts the countdown if timer is not already running.
     *
     * @param onTickEvent Callback to perform every timer's tick.
     * @param onFinishEvent Callback to perform when the countdown ends.
     */
    fun start(onTickEvent: ()->Unit, onFinishEvent:()->Unit) {
        if(isRunning){
            Log.d("Timer", "Timer already started. Stop it before restarting.")
            return
        }

        _timer = object : CountDownTimer(seconds.toLong()*1000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                _currentTimeMillis = millisUntilFinished
                onTickEvent()
            }
            override fun onFinish() {
                onFinishEvent()
            }
        }
        isRunning = true
    }

    /**
     * Stops and reset the countdown if timer is running.
     *
     * @param performOnFinishEvent Boolean to specify if the registered onFinish callback must be executed or not.
     */
    fun stop(performOnFinishEvent : Boolean = true) {
        if(!isRunning){
            Log.d("Timer", "Timer can't be stopped because it hasn't started yet.")
            return
        }
        _timer.cancel()
        _currentTimeMillis = 0

        if(performOnFinishEvent)
            _timer.onFinish()
    }
}