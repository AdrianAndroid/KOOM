#include <jni.h>
#include <string>
#include <vector>
#include <thread>
#include <android/log.h>
#include <jni.h>

#define NOINLINE __attribute__((noinline))
#define LOG_TAG "ThreadLeakTest"
#define LOGI(...) __android_log_print(ANDROID_LOG_INFO, LOG_TAG, __VA_ARGS__)

static NOINLINE void TestThreadLeak(int64_t delay) {
  std::thread test_thread([](int64_t delay) {
    pthread_setname_np(pthread_self(), "test_thread"); // 给线程设置名称
    LOGI("test_thread run");
    std::thread *test_thread_1; // 创建第一个线程
    std::thread *test_thread_2; // 创建第二个线程
    test_thread_1 = new std::thread([]() {
      pthread_setname_np(pthread_self(), "test_thread_1");
      LOGI("test_thread_1 run");
    });
    test_thread_2 = new std::thread([]() {
      pthread_setname_np(pthread_self(), "test_thread_2");
      LOGI("test_thread_2 run");
    });
    // 当前线程sleep几秒
    std::this_thread::sleep_for(std::chrono::milliseconds(delay));
    // 独立于当前线程运行
    test_thread_1->detach();
    LOGI("test_thread_1 detach");
    // 阻塞当前线程运行
    test_thread_2->join();
    LOGI("test_thread_2 join");
  }, delay);
  // 独立于主线程运行
  test_thread.detach();
}

extern "C"
JNIEXPORT void JNICALL
Java_com_kwai_koom_demo_threadleak_ThreadLeakTest_triggerLeak(
    JNIEnv *env,
    jclass, jlong delay) {
  TestThreadLeak(delay);
}