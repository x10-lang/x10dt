#ifndef __X10_LANG_RUNTIME_H
#define __X10_LANG_RUNTIME_H

#include <x10rt.h>


#define X10_LANG_OBJECT_H_NODEPS
#include <x10/lang/Object.h>
#undef X10_LANG_OBJECT_H_NODEPS
namespace x10 { namespace lang { 
class Any;
} } 
namespace x10 { namespace compiler { 
class Native;
} } 
namespace x10 { namespace lang { 
class String;
} } 
namespace x10 { namespace lang { 
class Boolean;
} } 
#include <x10/lang/Boolean.struct_h>
namespace x10 { namespace lang { 
class Int;
} } 
#include <x10/lang/Int.struct_h>
namespace x10 { namespace lang { 
class VoidFun_0_0;
} } 
namespace x10 { namespace lang { 
class FinishState;
} } 
namespace x10 { namespace compiler { 
class RemoteInvocation;
} } 
namespace x10 { namespace compiler { 
class TempNoInline_1;
} } 
namespace x10 { namespace lang { 
class Long;
} } 
#include <x10/lang/Long.struct_h>
namespace x10 { namespace lang { 
template<class FMGL(U)> class Fun_0_0;
} } 
namespace x10 { namespace lang { 
template<class FMGL(T)> class PlaceLocalHandle;
} } 
#include <x10/lang/PlaceLocalHandle.struct_h>
namespace x10 { namespace lang { 
class Runtime__Pool;
} } 
namespace x10 { namespace lang { 
class Monitor;
} } 
namespace x10 { namespace lang { 
class FinishState__FinishStates;
} } 
namespace x10 { namespace lang { 
class Runtime__Worker;
} } 
namespace x10 { namespace lang { 
class Thread;
} } 
namespace x10 { namespace lang { 
class Activity;
} } 
namespace x10 { namespace lang { 
class Place;
} } 
#include <x10/lang/Place.struct_h>
namespace x10 { namespace lang { 
class Throwable;
} } 
namespace x10 { namespace lang { 
class FinishState__Finish;
} } 
namespace x10 { namespace lang { 
class RuntimeException;
} } 
namespace x10 { namespace compiler { 
class Finalization;
} } 
namespace x10 { namespace array { 
template<class FMGL(T)> class Array;
} } 
namespace x10 { namespace lang { 
class Clock;
} } 
namespace x10 { namespace lang { 
class Activity__ClockPhases;
} } 
namespace x10 { namespace lang { 
class FinishState__UncountedFinish;
} } 
namespace x10 { namespace lang { 
class Runtime__RemoteControl;
} } 
namespace x10 { namespace compiler { 
class StackAllocate;
} } 
namespace x10 { namespace lang { 
template<class FMGL(T)> class GlobalRef;
} } 
#include <x10/lang/GlobalRef.struct_h>
namespace x10 { namespace lang { 
template<class FMGL(T)> class GlobalRef;
} } 
#include <x10/lang/GlobalRef.struct_h>
namespace x10 { namespace compiler { 
class AsyncClosure;
} } 
namespace x10 { namespace lang { 
template<class FMGL(U)> class Fun_0_0;
} } 
namespace x10 { namespace lang { 
template<class FMGL(T)> class Runtime__Remote;
} } 
namespace x10 { namespace lang { 
template<class FMGL(T)> class Runtime__Remote;
} } 
namespace x10 { namespace lang { 
template<class FMGL(T)> class GlobalRef;
} } 
#include <x10/lang/GlobalRef.struct_h>
namespace x10 { namespace lang { 
template<class FMGL(T)> class Runtime__Remote;
} } 
namespace x10 { namespace lang { 
template<class FMGL(T)> class GlobalRef;
} } 
#include <x10/lang/GlobalRef.struct_h>
namespace x10 { namespace lang { 
template<class FMGL(T)> class Runtime__Remote;
} } 
namespace x10 { namespace lang { 
template<class FMGL(T)> class Runtime__Remote;
} } 
namespace x10 { namespace util { 
template<class FMGL(T)> class Box;
} } 
namespace x10 { namespace lang { 
template<class FMGL(T)> class Runtime__Remote;
} } 
namespace x10 { namespace compiler { 
class Pragma;
} } 
namespace x10 { namespace lang { 
class FinishState__FinishAsync;
} } 
namespace x10 { namespace lang { 
class FinishState__FinishHere;
} } 
namespace x10 { namespace lang { 
class FinishState__FinishSPMD;
} } 
namespace x10 { namespace lang { 
class FinishState__LocalFinish;
} } 
namespace x10 { namespace lang { 
template<class FMGL(T)> class Reducible;
} } 
namespace x10 { namespace lang { 
template<class FMGL(T)> class FinishState__CollectingFinish;
} } 
namespace x10 { namespace lang { 
template<class FMGL(T)> class FinishState__CollectingFinish;
} } 
namespace x10 { namespace lang { 
template<class FMGL(T)> class FinishState__CollectingFinish;
} } 
namespace x10 { namespace compiler { 
class Pinned;
} } 
namespace x10 { namespace lang { 

class Runtime : public x10::lang::Object   {
    public:
    RTT_H_DECLS_CLASS
    
    void _instance_init();
    
    static x10_boolean FMGL(NO_STEALS);
    
    static inline x10_boolean FMGL(NO_STEALS__get)() {
        return x10::lang::Runtime::FMGL(NO_STEALS);
    }
    static x10_int FMGL(INIT_THREADS);
    
    static inline x10_int FMGL(INIT_THREADS__get)() {
        return x10::lang::Runtime::FMGL(INIT_THREADS);
    }
    static x10_int FMGL(MAX_WORKERS);
    
    static inline x10_int FMGL(MAX_WORKERS__get)() {
        return x10::lang::Runtime::FMGL(MAX_WORKERS);
    }
    static x10_boolean FMGL(STATIC_THREADS);
    
    static inline x10_boolean FMGL(STATIC_THREADS__get)() {
        return x10::lang::Runtime::FMGL(STATIC_THREADS);
    }
    static void runClosureAt(x10_int id, x10aux::ref<x10::lang::VoidFun_0_0> body);
    static void runClosureCopyAt(x10_int id, x10aux::ref<x10::lang::VoidFun_0_0> body);
    static void runAsyncAt(x10_int id, x10aux::ref<x10::lang::VoidFun_0_0> body,
                           x10aux::ref<x10::lang::FinishState> finishState);
    static void wsProcessEvents();
    static void wsRunAsync(x10_int id, x10aux::ref<x10::lang::VoidFun_0_0> body);
    static void wsRunCommand(x10_int id, x10aux::ref<x10::lang::VoidFun_0_0> body);
    static void runAtLocal(x10_int id, x10aux::ref<x10::lang::VoidFun_0_0> body);
    static x10_boolean isLocal(x10_int id);
    static void event_probe();
    static x10_long getAsyncsSent();
    static void setAsyncsSent(x10_long v);
    static x10_long getAsyncsReceived();
    static void setAsyncsReceived(x10_long v);
    static x10_long getSerializedBytes();
    static void setSerializedBytes(x10_long v);
    static x10_long getDeserializedBytes();
    static void setDeserializedBytes(x10_long v);
    static void deallocObject(x10aux::ref<x10::lang::Object> o);
    template<class FMGL(T)> static void dealloc(x10aux::ref<x10::lang::Fun_0_0<FMGL(T)> > o);
    static void dealloc(x10aux::ref<x10::lang::VoidFun_0_0> o);
    static x10_boolean FMGL(PRINT_STATS);
    
    static inline x10_boolean FMGL(PRINT_STATS__get)() {
        return x10::lang::Runtime::FMGL(PRINT_STATS);
    }
    static x10::lang::PlaceLocalHandle<x10aux::ref<x10::lang::Runtime> > FMGL(runtime);
    
    static void FMGL(runtime__do_init)();
    static void FMGL(runtime__init)();
    static volatile x10aux::status FMGL(runtime__status);
    static inline x10::lang::PlaceLocalHandle<x10aux::ref<x10::lang::Runtime> >
      FMGL(runtime__get)() {
        if (FMGL(runtime__status) != x10aux::INITIALIZED) {
            FMGL(runtime__init)();
        }
        return x10::lang::Runtime::FMGL(runtime);
    }
    static x10aux::ref<x10::lang::Reference>
      FMGL(runtime__deserialize)(x10aux::deserialization_buffer &buf);
    static const x10aux::serialization_id_t FMGL(runtime__id);
    
    x10aux::ref<x10::lang::Runtime__Pool>
      FMGL(pool);
    
    x10aux::ref<x10::lang::Monitor>
      FMGL(atomicMonitor);
    
    x10aux::ref<x10::lang::Monitor>
      FMGL(staticMonitor);
    
    x10aux::ref<x10::lang::FinishState__FinishStates>
      FMGL(finishStates);
    
    void _constructor(
      x10aux::ref<x10::lang::Runtime__Pool> pool);
    
    static x10aux::ref<x10::lang::Runtime> _make(
             x10aux::ref<x10::lang::Runtime__Pool> pool);
    
    static x10aux::ref<x10::lang::Runtime__Worker>
      worker(
      );
    static x10_int
      workerId(
      );
    static x10_int
      poolSize(
      );
    static x10aux::ref<x10::lang::Activity>
      activity(
      );
    static x10::lang::Place
      home(
      );
    static x10_int
      hereInt(
      );
    static x10_int
      surplusActivityCount(
      );
    static void
      start(
      x10aux::ref<x10::lang::VoidFun_0_0> init,
      x10aux::ref<x10::lang::VoidFun_0_0> body);
    static void
      runAsync(
      x10::lang::Place place,
      x10aux::ref<x10::array::Array<x10aux::ref<x10::lang::Clock> > > clocks,
      x10aux::ref<x10::lang::VoidFun_0_0> body);
    static void
      runAsync(
      x10::lang::Place place,
      x10aux::ref<x10::lang::VoidFun_0_0> body);
    static void
      runAsync(
      x10aux::ref<x10::array::Array<x10aux::ref<x10::lang::Clock> > > clocks,
      x10aux::ref<x10::lang::VoidFun_0_0> body);
    static void
      runAsync(
      x10aux::ref<x10::lang::VoidFun_0_0> body);
    static void
      runFinish(
      x10aux::ref<x10::lang::VoidFun_0_0> body);
    static void
      runUncountedAsync(
      x10::lang::Place place,
      x10aux::ref<x10::lang::VoidFun_0_0> body);
    static void
      runUncountedAsync(
      x10aux::ref<x10::lang::VoidFun_0_0> body);
    static void
      runAt(
      x10::lang::Place place,
      x10aux::ref<x10::lang::VoidFun_0_0> body);
    template<class FMGL(T)>
    static FMGL(T)
      evalAt(
      x10::lang::Place place,
      x10aux::ref<x10::lang::Fun_0_0<FMGL(T)> > eval);
    static void
      StaticInitBroadcastDispatcherLock(
      );
    static void
      StaticInitBroadcastDispatcherAwait(
      );
    static void
      StaticInitBroadcastDispatcherUnlock(
      );
    static void
      StaticInitBroadcastDispatcherNotify(
      );
    static void
      enterAtomic(
      );
    static void
      ensureNotInAtomic(
      );
    static void
      exitAtomic(
      );
    static void
      awaitAtomic(
      );
    static void
      next(
      );
    static void
      resume(
      );
    static x10aux::ref<x10::lang::FinishState>
      startFinish(
      );
    static x10aux::ref<x10::lang::FinishState>
      startFinish(
      x10_int pragma);
    static x10aux::ref<x10::lang::FinishState>
      startLocalFinish(
      );
    static x10aux::ref<x10::lang::FinishState>
      startSimpleFinish(
      );
    static void
      stopFinish(
      x10aux::ref<x10::lang::FinishState> f);
    static void
      pushException(
      x10aux::ref<x10::lang::Throwable> t);
    template<class FMGL(T)>
    static x10aux::ref<x10::lang::FinishState>
      startCollectingFinish(
      x10aux::ref<x10::lang::Reducible<FMGL(T)> > r);
    template<class FMGL(T)>
    static void
      makeOffer(
      FMGL(T) t);
    template<class FMGL(T)>
    static FMGL(T)
      stopCollectingFinish(
      x10aux::ref<x10::lang::FinishState> f);
    static void
      execute(
      x10aux::ref<x10::lang::Activity> activity);
    static void
      execute(
      x10aux::ref<x10::lang::VoidFun_0_0> body,
      x10aux::ref<x10::lang::FinishState> finishState);
    static void
      probe(
      );
    static void
      increaseParallelism(
      );
    static void
      decreaseParallelism(
      x10_int n);
    virtual x10aux::ref<x10::lang::Runtime>
      x10__lang__Runtime____x10__lang__Runtime__this(
      );
    
    // Serialization
    public: static const x10aux::serialization_id_t _serialization_id;
    
    public: x10aux::serialization_id_t _get_serialization_id() {
         return _serialization_id;
    }
    
    public: virtual void _serialize_body(x10aux::serialization_buffer& buf);
    
    public: template<class __T> static x10aux::ref<__T> _deserializer(x10aux::deserialization_buffer& buf);
    
    public: void _deserialize_body(x10aux::deserialization_buffer& buf);
    
};

} } 
#endif // X10_LANG_RUNTIME_H

namespace x10 { namespace lang { 
class Runtime;
} } 

#ifndef X10_LANG_RUNTIME_H_NODEPS
#define X10_LANG_RUNTIME_H_NODEPS
#include <x10/lang/Object.h>
#include <x10/lang/Any.h>
#include <x10/compiler/Native.h>
#include <x10/lang/String.h>
#include <x10/lang/Boolean.h>
#include <x10/lang/Int.h>
#include <x10/lang/VoidFun_0_0.h>
#include <x10/lang/FinishState.h>
#include <x10/compiler/RemoteInvocation.h>
#include <x10/compiler/TempNoInline_1.h>
#include <x10/lang/Long.h>
#include <x10/lang/Fun_0_0.h>
#include <x10/lang/PlaceLocalHandle.h>
#include <x10/lang/Runtime__Pool.h>
#include <x10/lang/Monitor.h>
#include <x10/lang/FinishState__FinishStates.h>
#include <x10/lang/Runtime__Worker.h>
#include <x10/lang/Thread.h>
#include <x10/lang/Activity.h>
#include <x10/lang/Place.h>
#include <x10/lang/Throwable.h>
#include <x10/lang/FinishState__Finish.h>
#include <x10/lang/RuntimeException.h>
#include <x10/compiler/Finalization.h>
#include <x10/array/Array.h>
#include <x10/lang/Clock.h>
#include <x10/lang/Activity__ClockPhases.h>
#include <x10/lang/FinishState__UncountedFinish.h>
#include <x10/lang/Runtime__RemoteControl.h>
#include <x10/compiler/StackAllocate.h>
#include <x10/lang/GlobalRef.h>
#include <x10/lang/GlobalRef.h>
#include <x10/compiler/AsyncClosure.h>
#include <x10/lang/Fun_0_0.h>
#include <x10/lang/Runtime__Remote.h>
#include <x10/lang/Runtime__Remote.h>
#include <x10/lang/GlobalRef.h>
#include <x10/lang/Runtime__Remote.h>
#include <x10/lang/GlobalRef.h>
#include <x10/lang/Runtime__Remote.h>
#include <x10/lang/Runtime__Remote.h>
#include <x10/util/Box.h>
#include <x10/lang/Runtime__Remote.h>
#include <x10/compiler/Pragma.h>
#include <x10/lang/FinishState__FinishAsync.h>
#include <x10/lang/FinishState__FinishHere.h>
#include <x10/lang/FinishState__FinishSPMD.h>
#include <x10/lang/FinishState__LocalFinish.h>
#include <x10/lang/Reducible.h>
#include <x10/lang/FinishState__CollectingFinish.h>
#include <x10/lang/FinishState__CollectingFinish.h>
#include <x10/lang/FinishState__CollectingFinish.h>
#include <x10/compiler/Pinned.h>
#ifndef X10_LANG_RUNTIME__CLOSURE__12_CLOSURE
#define X10_LANG_RUNTIME__CLOSURE__12_CLOSURE
#include <x10/lang/Closure.h>
#include <x10/lang/VoidFun_0_0.h>
template<class FMGL(T)> class x10_lang_Runtime__closure__12 : public x10::lang::Closure {
    public:
    
    static typename x10::lang::VoidFun_0_0::template itable <x10_lang_Runtime__closure__12<FMGL(T) > > _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    void __apply() {
        
        //#line 786 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/Runtime.x10": x10.ast.X10LocalDecl_c
        x10aux::ref<x10::lang::Runtime__Remote<FMGL(T)> > me2 = (box)->__apply();
        
        //#line 787 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/Runtime.x10": polyglot.ast.Eval_c
        me2->FMGL(t) = x10::util::Box<void>::__implicit_convert<FMGL(T) >(
                         result);
        
        //#line 788 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/Runtime.x10": polyglot.ast.Eval_c
        me2->FMGL(clockPhases) = clockPhases;
        
        //#line 789 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/Runtime.x10": polyglot.ast.Eval_c
        me2->release();
    }
    
    // captured environment
    x10::lang::GlobalRef<x10aux::ref<x10::lang::Runtime__Remote<FMGL(T)> > > box;
    FMGL(T) result;
    x10aux::ref<x10::lang::Activity__ClockPhases> clockPhases;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->box);
        buf.write(this->result);
        buf.write(this->clockPhases);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        x10_lang_Runtime__closure__12<FMGL(T) >* storage = x10aux::alloc<x10_lang_Runtime__closure__12<FMGL(T) > >();
        buf.record_reference(x10aux::ref<x10_lang_Runtime__closure__12<FMGL(T) > >(storage));
        x10::lang::GlobalRef<x10aux::ref<x10::lang::Runtime__Remote<FMGL(T)> > > that_box = buf.read<x10::lang::GlobalRef<x10aux::ref<x10::lang::Runtime__Remote<FMGL(T)> > > >();
        FMGL(T) that_result = buf.read<FMGL(T)>();
        x10aux::ref<x10::lang::Activity__ClockPhases> that_clockPhases = buf.read<x10aux::ref<x10::lang::Activity__ClockPhases> >();
        x10aux::ref<x10_lang_Runtime__closure__12<FMGL(T) > > this_ = new (storage) x10_lang_Runtime__closure__12<FMGL(T) >(that_box, that_result, that_clockPhases);
        return this_;
    }
    
    x10_lang_Runtime__closure__12(x10::lang::GlobalRef<x10aux::ref<x10::lang::Runtime__Remote<FMGL(T)> > > box, FMGL(T) result, x10aux::ref<x10::lang::Activity__ClockPhases> clockPhases) : box(box), result(result), clockPhases(clockPhases) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::VoidFun_0_0>(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::VoidFun_0_0>(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/Runtime.x10:785-790";
    }

};

template<class FMGL(T)> typename x10::lang::VoidFun_0_0::template itable <x10_lang_Runtime__closure__12<FMGL(T) > >x10_lang_Runtime__closure__12<FMGL(T) >::_itable(&x10::lang::Reference::equals, &x10::lang::Closure::hashCode, &x10_lang_Runtime__closure__12<FMGL(T) >::__apply, &x10_lang_Runtime__closure__12<FMGL(T) >::toString, &x10::lang::Closure::typeName);template<class FMGL(T)>
x10aux::itable_entry x10_lang_Runtime__closure__12<FMGL(T) >::_itables[2] = {x10aux::itable_entry(&x10aux::getRTT<x10::lang::VoidFun_0_0>, &x10_lang_Runtime__closure__12<FMGL(T) >::_itable),x10aux::itable_entry(NULL, NULL)};

template<class FMGL(T)>
const x10aux::serialization_id_t x10_lang_Runtime__closure__12<FMGL(T) >::_serialization_id = 
    x10aux::DeserializationDispatcher::addDeserializer(x10_lang_Runtime__closure__12<FMGL(T) >::template _deserialize<x10::lang::Reference>,x10aux::CLOSURE_KIND_SIMPLE_ASYNC);

#endif // X10_LANG_RUNTIME__CLOSURE__12_CLOSURE
#ifndef X10_LANG_RUNTIME__CLOSURE__13_CLOSURE
#define X10_LANG_RUNTIME__CLOSURE__13_CLOSURE
#include <x10/lang/Closure.h>
#include <x10/lang/VoidFun_0_0.h>
template<class FMGL(T)> class x10_lang_Runtime__closure__13 : public x10::lang::Closure {
    public:
    
    static typename x10::lang::VoidFun_0_0::template itable <x10_lang_Runtime__closure__13<FMGL(T) > > _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    void __apply() {
        
        //#line 793 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/Runtime.x10": x10.ast.X10LocalDecl_c
        x10aux::ref<x10::lang::Runtime__Remote<FMGL(T)> > me2 = (box)->__apply();
        
        //#line 794 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/Runtime.x10": polyglot.ast.Eval_c
        me2->FMGL(e) = e;
        
        //#line 795 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/Runtime.x10": polyglot.ast.Eval_c
        me2->FMGL(clockPhases) = clockPhases;
        
        //#line 796 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/Runtime.x10": polyglot.ast.Eval_c
        me2->release();
    }
    
    // captured environment
    x10::lang::GlobalRef<x10aux::ref<x10::lang::Runtime__Remote<FMGL(T)> > > box;
    x10aux::ref<x10::lang::Throwable> e;
    x10aux::ref<x10::lang::Activity__ClockPhases> clockPhases;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->box);
        buf.write(this->e);
        buf.write(this->clockPhases);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        x10_lang_Runtime__closure__13<FMGL(T) >* storage = x10aux::alloc<x10_lang_Runtime__closure__13<FMGL(T) > >();
        buf.record_reference(x10aux::ref<x10_lang_Runtime__closure__13<FMGL(T) > >(storage));
        x10::lang::GlobalRef<x10aux::ref<x10::lang::Runtime__Remote<FMGL(T)> > > that_box = buf.read<x10::lang::GlobalRef<x10aux::ref<x10::lang::Runtime__Remote<FMGL(T)> > > >();
        x10aux::ref<x10::lang::Throwable> that_e = buf.read<x10aux::ref<x10::lang::Throwable> >();
        x10aux::ref<x10::lang::Activity__ClockPhases> that_clockPhases = buf.read<x10aux::ref<x10::lang::Activity__ClockPhases> >();
        x10aux::ref<x10_lang_Runtime__closure__13<FMGL(T) > > this_ = new (storage) x10_lang_Runtime__closure__13<FMGL(T) >(that_box, that_e, that_clockPhases);
        return this_;
    }
    
    x10_lang_Runtime__closure__13(x10::lang::GlobalRef<x10aux::ref<x10::lang::Runtime__Remote<FMGL(T)> > > box, x10aux::ref<x10::lang::Throwable> e, x10aux::ref<x10::lang::Activity__ClockPhases> clockPhases) : box(box), e(e), clockPhases(clockPhases) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::VoidFun_0_0>(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::VoidFun_0_0>(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/Runtime.x10:792-797";
    }

};

template<class FMGL(T)> typename x10::lang::VoidFun_0_0::template itable <x10_lang_Runtime__closure__13<FMGL(T) > >x10_lang_Runtime__closure__13<FMGL(T) >::_itable(&x10::lang::Reference::equals, &x10::lang::Closure::hashCode, &x10_lang_Runtime__closure__13<FMGL(T) >::__apply, &x10_lang_Runtime__closure__13<FMGL(T) >::toString, &x10::lang::Closure::typeName);template<class FMGL(T)>
x10aux::itable_entry x10_lang_Runtime__closure__13<FMGL(T) >::_itables[2] = {x10aux::itable_entry(&x10aux::getRTT<x10::lang::VoidFun_0_0>, &x10_lang_Runtime__closure__13<FMGL(T) >::_itable),x10aux::itable_entry(NULL, NULL)};

template<class FMGL(T)>
const x10aux::serialization_id_t x10_lang_Runtime__closure__13<FMGL(T) >::_serialization_id = 
    x10aux::DeserializationDispatcher::addDeserializer(x10_lang_Runtime__closure__13<FMGL(T) >::template _deserialize<x10::lang::Reference>,x10aux::CLOSURE_KIND_SIMPLE_ASYNC);

#endif // X10_LANG_RUNTIME__CLOSURE__13_CLOSURE
#ifndef X10_LANG_RUNTIME__CLOSURE__11_CLOSURE
#define X10_LANG_RUNTIME__CLOSURE__11_CLOSURE
#include <x10/lang/Closure.h>
#include <x10/lang/VoidFun_0_0.h>
template<class FMGL(T)> class x10_lang_Runtime__closure__11 : public x10::lang::Closure {
    public:
    
    static typename x10::lang::VoidFun_0_0::template itable <x10_lang_Runtime__closure__11<FMGL(T) > > _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    void __apply() {
        
        //#line 782 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/Runtime.x10": polyglot.ast.Eval_c
        x10aux::nullCheck(x10::lang::Runtime::activity())->FMGL(clockPhases) =
          clockPhases;
        
        //#line 783 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/Runtime.x10": polyglot.ast.Try_c
        try {
            
            //#line 784 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/Runtime.x10": x10.ast.X10LocalDecl_c
            FMGL(T) result = x10::lang::Fun_0_0<FMGL(T)>::__apply(x10aux::nullCheck(eval));
            
            //#line 785 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/Runtime.x10": polyglot.ast.Eval_c
            x10::lang::Runtime::runAsync(x10::lang::Place_methods::place((box)->location),
                                         x10aux::class_cast_unchecked<x10aux::ref<x10::lang::VoidFun_0_0> >(x10aux::ref<x10::lang::VoidFun_0_0>(x10aux::ref<x10_lang_Runtime__closure__12<FMGL(T) > >(new (x10aux::alloc<x10::lang::VoidFun_0_0>(sizeof(x10_lang_Runtime__closure__12<FMGL(T)>)))x10_lang_Runtime__closure__12<FMGL(T)>(box, result, clockPhases)))));
        }
        catch (x10aux::__ref& __ref__1461) {
            x10aux::ref<x10::lang::Throwable>& __exc__ref__1461 = (x10aux::ref<x10::lang::Throwable>&)__ref__1461;
            if (x10aux::instanceof<x10aux::ref<x10::lang::Throwable> >(__exc__ref__1461)) {
                x10aux::ref<x10::lang::Throwable> e = static_cast<x10aux::ref<x10::lang::Throwable> >(__exc__ref__1461);
                {
                    
                    //#line 792 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/Runtime.x10": polyglot.ast.Eval_c
                    x10::lang::Runtime::runAsync(x10::lang::Place_methods::place((box)->location),
                                                 x10aux::class_cast_unchecked<x10aux::ref<x10::lang::VoidFun_0_0> >(x10aux::ref<x10::lang::VoidFun_0_0>(x10aux::ref<x10_lang_Runtime__closure__13<FMGL(T) > >(new (x10aux::alloc<x10::lang::VoidFun_0_0>(sizeof(x10_lang_Runtime__closure__13<FMGL(T)>)))x10_lang_Runtime__closure__13<FMGL(T)>(box, e, clockPhases)))));
                }
            } else
            throw;
        }
        
        //#line 799 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/Runtime.x10": polyglot.ast.Eval_c
        x10aux::nullCheck(x10::lang::Runtime::activity())->
          FMGL(clockPhases) = x10aux::class_cast_unchecked<x10aux::ref<x10::lang::Activity__ClockPhases> >(X10_NULL);
    }
    
    // captured environment
    x10aux::ref<x10::lang::Activity__ClockPhases> clockPhases;
    x10aux::ref<x10::lang::Fun_0_0<FMGL(T)> > eval;
    x10::lang::GlobalRef<x10aux::ref<x10::lang::Runtime__Remote<FMGL(T)> > > box;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->clockPhases);
        buf.write(this->eval);
        buf.write(this->box);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        x10_lang_Runtime__closure__11<FMGL(T) >* storage = x10aux::alloc<x10_lang_Runtime__closure__11<FMGL(T) > >();
        buf.record_reference(x10aux::ref<x10_lang_Runtime__closure__11<FMGL(T) > >(storage));
        x10aux::ref<x10::lang::Activity__ClockPhases> that_clockPhases = buf.read<x10aux::ref<x10::lang::Activity__ClockPhases> >();
        x10aux::ref<x10::lang::Fun_0_0<FMGL(T)> > that_eval = buf.read<x10aux::ref<x10::lang::Fun_0_0<FMGL(T)> > >();
        x10::lang::GlobalRef<x10aux::ref<x10::lang::Runtime__Remote<FMGL(T)> > > that_box = buf.read<x10::lang::GlobalRef<x10aux::ref<x10::lang::Runtime__Remote<FMGL(T)> > > >();
        x10aux::ref<x10_lang_Runtime__closure__11<FMGL(T) > > this_ = new (storage) x10_lang_Runtime__closure__11<FMGL(T) >(that_clockPhases, that_eval, that_box);
        return this_;
    }
    
    x10_lang_Runtime__closure__11(x10aux::ref<x10::lang::Activity__ClockPhases> clockPhases, x10aux::ref<x10::lang::Fun_0_0<FMGL(T)> > eval, x10::lang::GlobalRef<x10aux::ref<x10::lang::Runtime__Remote<FMGL(T)> > > box) : clockPhases(clockPhases), eval(eval), box(box) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::VoidFun_0_0>(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::VoidFun_0_0>(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/Runtime.x10:781-800";
    }

};

template<class FMGL(T)> typename x10::lang::VoidFun_0_0::template itable <x10_lang_Runtime__closure__11<FMGL(T) > >x10_lang_Runtime__closure__11<FMGL(T) >::_itable(&x10::lang::Reference::equals, &x10::lang::Closure::hashCode, &x10_lang_Runtime__closure__11<FMGL(T) >::__apply, &x10_lang_Runtime__closure__11<FMGL(T) >::toString, &x10::lang::Closure::typeName);template<class FMGL(T)>
x10aux::itable_entry x10_lang_Runtime__closure__11<FMGL(T) >::_itables[2] = {x10aux::itable_entry(&x10aux::getRTT<x10::lang::VoidFun_0_0>, &x10_lang_Runtime__closure__11<FMGL(T) >::_itable),x10aux::itable_entry(NULL, NULL)};

template<class FMGL(T)>
const x10aux::serialization_id_t x10_lang_Runtime__closure__11<FMGL(T) >::_serialization_id = 
    x10aux::DeserializationDispatcher::addDeserializer(x10_lang_Runtime__closure__11<FMGL(T) >::template _deserialize<x10::lang::Reference>,x10aux::CLOSURE_KIND_SIMPLE_ASYNC);

#endif // X10_LANG_RUNTIME__CLOSURE__11_CLOSURE
#ifndef X10_LANG_RUNTIME_H_GENERICS
#define X10_LANG_RUNTIME_H_GENERICS
#ifndef X10_LANG_RUNTIME_H_dealloc_1433
#define X10_LANG_RUNTIME_H_dealloc_1433
template<class FMGL(T)> void x10::lang::Runtime::dealloc(x10aux::ref<x10::lang::Fun_0_0<FMGL(T)> > o) {
 
}
#endif // X10_LANG_RUNTIME_H_dealloc_1433
#ifndef X10_LANG_RUNTIME_H_evalAt_1460
#define X10_LANG_RUNTIME_H_evalAt_1460
template<class FMGL(T)> FMGL(T) x10::lang::Runtime::evalAt(x10::lang::Place place,
                                                           x10aux::ref<x10::lang::Fun_0_0<FMGL(T)> > eval) {
    
    //#line 774 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/Runtime.x10": polyglot.ast.Eval_c
    x10::lang::Runtime::ensureNotInAtomic();
    
    //#line 775 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/Runtime.x10": x10.ast.X10If_c
    if ((x10aux::struct_equals(place->FMGL(id), x10aux::here))) {
        
        //#line 776 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/Runtime.x10": x10.ast.X10Return_c
        return x10::lang::Fun_0_0<FMGL(T)>::__apply(x10aux::nullCheck(x10aux::deep_copy<x10aux::ref<x10::lang::Fun_0_0<FMGL(T)> > >(eval)));
        
    }
    
    //#line 778 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/Runtime.x10": x10.ast.X10LocalDecl_c
    x10::lang::Runtime__Remote<FMGL(T)> _StackAllocate_me;
     _StackAllocate_me._constructor();
    x10aux::ref<x10::lang::Runtime__Remote<FMGL(T)> > me(&_StackAllocate_me);
    
    //#line 779 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/Runtime.x10": x10.ast.X10LocalDecl_c
    x10::lang::GlobalRef<x10aux::ref<x10::lang::Runtime__Remote<FMGL(T)> > > box =
      x10::lang::GlobalRef_methods<x10aux::ref<x10::lang::Runtime__Remote<FMGL(T)> > >::_make(me);
    
    //#line 780 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/Runtime.x10": x10.ast.X10LocalDecl_c
    x10aux::ref<x10::lang::Activity__ClockPhases> clockPhases =
      x10aux::nullCheck(x10::lang::Runtime::activity())->
        FMGL(clockPhases);
    
    //#line 781 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/Runtime.x10": polyglot.ast.Eval_c
    x10::lang::Runtime::runAsync(place, x10aux::class_cast_unchecked<x10aux::ref<x10::lang::VoidFun_0_0> >(x10aux::ref<x10::lang::VoidFun_0_0>(x10aux::ref<x10_lang_Runtime__closure__11<FMGL(T) > >(new (x10aux::alloc<x10::lang::VoidFun_0_0>(sizeof(x10_lang_Runtime__closure__11<FMGL(T)>)))x10_lang_Runtime__closure__11<FMGL(T)>(clockPhases, eval, box)))));
    
    //#line 801 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/Runtime.x10": polyglot.ast.Eval_c
    me->await();
    
    //#line 802 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/Runtime.x10": polyglot.ast.Eval_c
    x10aux::dealloc(eval.operator->());
    
    //#line 803 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/Runtime.x10": polyglot.ast.Eval_c
    x10aux::nullCheck(x10::lang::Runtime::activity())->FMGL(clockPhases) =
      me->
        FMGL(clockPhases);
    
    //#line 804 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/Runtime.x10": x10.ast.X10If_c
    if ((!x10aux::struct_equals(X10_NULL, me->FMGL(e)))) {
        
        //#line 805 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/Runtime.x10": polyglot.ast.Throw_c
        x10aux::throwException(x10aux::nullCheck(me->FMGL(e)));
    }
    
    //#line 807 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/Runtime.x10": x10.ast.X10Return_c
    return x10aux::nullCheck(me->FMGL(t))->FMGL(value);
    
}
#endif // X10_LANG_RUNTIME_H_evalAt_1460
#ifndef X10_LANG_RUNTIME_H_startCollectingFinish_1478
#define X10_LANG_RUNTIME_H_startCollectingFinish_1478
template<class FMGL(T)> x10aux::ref<x10::lang::FinishState>
  x10::lang::Runtime::startCollectingFinish(
  x10aux::ref<x10::lang::Reducible<FMGL(T)> > r) {
    
    //#line 925 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/Runtime.x10": x10.ast.X10Return_c
    return x10aux::nullCheck(x10::lang::Runtime::activity())->swapFinish(
             x10aux::class_cast_unchecked<x10aux::ref<x10::lang::FinishState> >(x10::lang::FinishState__CollectingFinish<FMGL(T)>::_make(r)));
    
}
#endif // X10_LANG_RUNTIME_H_startCollectingFinish_1478
#ifndef X10_LANG_RUNTIME_H_makeOffer_1479
#define X10_LANG_RUNTIME_H_makeOffer_1479
template<class FMGL(T)> void x10::lang::Runtime::makeOffer(
  FMGL(T) t) {
    
    //#line 929 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/Runtime.x10": x10.ast.X10LocalDecl_c
    x10aux::ref<x10::lang::FinishState> state = x10aux::nullCheck(x10::lang::Runtime::activity())->finishState();
    
    //#line 931 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/Runtime.x10": polyglot.ast.Eval_c
    x10aux::nullCheck((x10aux::class_cast<x10aux::ref<x10::lang::FinishState__CollectingFinish<FMGL(T)> > >(state)))->accept(
      t,
      x10::lang::Runtime::workerId());
}
#endif // X10_LANG_RUNTIME_H_makeOffer_1479
#ifndef X10_LANG_RUNTIME_H_stopCollectingFinish_1480
#define X10_LANG_RUNTIME_H_stopCollectingFinish_1480
template<class FMGL(T)> FMGL(T) x10::lang::Runtime::stopCollectingFinish(
  x10aux::ref<x10::lang::FinishState> f) {
    
    //#line 935 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/Runtime.x10": x10.ast.X10LocalDecl_c
    x10aux::ref<x10::lang::FinishState> state = x10aux::nullCheck(x10::lang::Runtime::activity())->swapFinish(
                                                  f);
    
    //#line 936 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/Runtime.x10": x10.ast.X10Return_c
    return x10aux::nullCheck((x10aux::class_cast<x10aux::ref<x10::lang::FinishState__CollectingFinish<FMGL(T)> > >(state)))->waitForFinishExpr();
    
}
#endif // X10_LANG_RUNTIME_H_stopCollectingFinish_1480
template<class __T> x10aux::ref<__T> x10::lang::Runtime::_deserializer(x10aux::deserialization_buffer& buf) {
    x10aux::ref<x10::lang::Runtime> this_ = new (memset(x10aux::alloc<x10::lang::Runtime>(), 0, sizeof(x10::lang::Runtime))) x10::lang::Runtime();
    buf.record_reference(this_);
    this_->_deserialize_body(buf);
    return this_;
}

#endif // X10_LANG_RUNTIME_H_GENERICS
#endif // __X10_LANG_RUNTIME_H_NODEPS
